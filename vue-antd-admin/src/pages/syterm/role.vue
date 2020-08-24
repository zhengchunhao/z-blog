<template>
  <a-card>
    <a-modal v-model="authorizeModal" title="角色授权">
      <template slot="footer">
        <a-button key="back" @click="authorizeCancel">取消</a-button>
        <a-button key="submit" type="primary" @click="authorizeSubmit">提交</a-button>
      </template>
      <a-tree v-model="checkedKeys" checkable :selected-keys="selectedKeys" :tree-data="treeData" />
    </a-modal>
    <a-modal v-model="visible" title="角色管理" @cancel="handleCancel">
      <template slot="footer">
        <a-button key="back" @click="handleCancel">取消</a-button>
        <a-button key="submit" type="primary" @click="handleSubmit">提交</a-button>
      </template>
      <a-form-model
        ref="roleForm"
        :model="roleForm"
        :rules="rules"
        :label-col="labelCol"
        :wrapper-col="wrapperCol"
      >
        <a-form-model-item label="名称" has-feedback prop="name">
          <a-input v-model="roleForm.name" placeholder="请输入角色名称" />
        </a-form-model-item>
        <a-form-model-item label="键值" has-feedback prop="roleKey">
          <a-input v-model="roleForm.roleKey" placeholder="请输入角色键值" />
        </a-form-model-item>
        <a-form-model-item label="角色描述" has-feedback prop="remark">
          <a-input v-model="roleForm.remark" placeholder="请输入对应组件名称" />
        </a-form-model-item>
      </a-form-model>
    </a-modal>

    <div :class="advanced ? 'search' : null">
      <a-form-model ref="queryForm" :model="queryForm" layout="horizontal">
        <div :class="advanced ? null: 'fold'">
          <a-row>
            <a-col :md="8" :sm="24">
              <a-form-model-item
                label="名称"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
              >
                <a-input placeholder="请输入角色名称" v-model="queryForm.name" />
              </a-form-model-item>
            </a-col>
            <a-col :md="8" :sm="24">
              <a-form-model-item
                label="键值"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
              >
                <a-input placeholder="请输入用户名" v-model="queryForm.roleKey" />
              </a-form-model-item>
            </a-col>
            <a-col :md="8" :sm="24">
              <a-form-model-item
                label="创建日期"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
              >
                <div>
                  <a-range-picker @change="onPickerChange" />
                </div>
              </a-form-model-item>
            </a-col>
          </a-row>
        </div>
        <span style="float: right; margin-top: 3px;">
          <a-button type="primary" @click="query">查询</a-button>
          <a-button style="margin-left: 8px" @click="reset">重置</a-button>
        </span>
      </a-form-model>
    </div>
    <div>
      <div class="operator">
        <a-button @click="openModal()" type="primary">新增</a-button>
        <a-dropdown>
          <a-menu @click="handleMenuClick" slot="overlay">
            <a-menu-item key="delete">删除</a-menu-item>
            <a-menu-item key="audit">审批</a-menu-item>
          </a-menu>
          <a-button>
            批量操作
            <a-icon type="down" />
          </a-button>
        </a-dropdown>
      </div>
      <standard-table
        :columns="columns"
        :dataSource="dataSource"
        :selectedRows.sync="selectedRows"
        :rowKey="record=>record.id"
        @clear="onClear"
        @change="onChange"
        @selectedRowChange="onSelectChange"
      >
        <div slot="description" slot-scope="{text}">{{text}}</div>
        <div slot="action" slot-scope="{record}">
          <a style="margin-right: 8px" @click="authorizePerms(record)">
            <a-icon type="plus" />授权
          </a>
          <a style="margin-right: 8px" @click="openModal(record)">
            <a-icon type="edit" />编辑
          </a>
          <a>
            <a-icon type="delete" />删除
          </a>
        </div>
        <template slot="statusTitle">
          <a-icon @click.native="onStatusTitleClick" type="info-circle" />
        </template>
      </standard-table>
    </div>
  </a-card>
</template>

<script>
import StandardTable from "@/components/table/StandardTable";
import {
  roleList,
  add,
  update,
  remove,
  role,
  authorize,
  permsTree,
  rolePermsTree,
  checkRoleKeyUnique,
} from "@/services/systerm/role";
const columns = [
  {
    title: "名称",
    dataIndex: "name",
    align: "center",
  },
  {
    title: "角色键值",
    dataIndex: "roleKey",
    align: "center",
  },
  {
    title: "角色描述",
    dataIndex: "remark",
    align: "center",
  },
  {
    title: "更新时间",
    dataIndex: "updatedAt",
    sorter: true,
    align: "center",
  },
  {
    title: "创建人",
    dataIndex: "createBy",
    align: "center",
  },
  {
    title: "创建时间",
    dataIndex: "createTime",
    align: "center",
  },

  {
    title: "修改人",
    dataIndex: "editBy",
    align: "center",
  },
  {
    title: "修改时间",
    dataIndex: "editTime",
    align: "center",
  },
  {
    title: "操作",
    scopedSlots: { customRender: "action" },
  },
];
export default {
  name: "role",
  components: { StandardTable },
  data() {
    let checkRoleKey = (rule, value, callback) => {
      if (value == null || value == undefined || !value.length > 0) {
        return callback(new Error("请填写角色键值"));
      } else if (value.length > 0) {
        var params = {
          roleKey: value,
          id: this.roleForm.id,
        };
        checkRoleKeyUnique(params).then((res) => {
          if (res) {
            return callback(new Error("角色键值已存在"));
          } else {
            return callback();
          }
        });
      } else {
        return callback();
      }
    };
    return {
      labelCol: { span: 6 },
      wrapperCol: { span: 14 },
      advanced: false,
      visible: false,
      authorizeModal: false,
      columns: columns,
      dataSource: [],
      selectedRows: [],
      roleForm: {},
      //已选键值
      checkedKeys: [],
      //树形结构
      treeData: [],
      selectedKeys: [],
      roleId: undefined,
      queryForm: {
        name: undefined,
        roleKey: undefined,
        startTime: undefined,
        endTime: undefined,
        pageNum: 1,
        pageSize: 10,
      },
      pagination: {
        pageSize: 10,
        current: 1,
        total: 0,
        showSizeChanger: true,
        showTotal: (total) => `总共 ${total} 条记录`,
      },
      rules: {
        name: [{ required: true, message: "请输入角色名称", trigger: "blur" }],
        roleKey: [{ required: true, validator: checkRoleKey }],
        remark: [
          { required: true, message: "请输入角色介绍", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getRoleList();
  },
  watch: {
    checkedKeys(val) {
      this.checkedKeys = val;
    },
  },
  methods: {
    //角色列表
    getRoleList() {
      roleList(this.queryForm).then((res) => {
        if (res.code == 200) {
          this.dataSource = res.rows;
        }
      });
    },
    //对话框取消
    handleCancel() {
      this.visible = !this.visible;
      this.resetForm();
    },
    //打开对话框
    openModal(record) {
      this.visible = !this.visible;
      if (record) {
        this.roleForm = record;
      }
    },
    //表单提交
    handleSubmit() {
      this.$refs.roleForm.validate((valid) => {
        if (valid) {
          if (this.roleForm.id) {
            update(this.roleForm).then((res) => {
              if (res.code == 200) {
                this.getRoleList();
                this.resetForm();
                this.$message.success("操作成功");
                this.visible = !this.visible;
              }
            });
          } else {
            add(this.roleForm).then((res) => {
              if (res.code == 200) {
                this.getRoleList();
                this.resetForm();
                this.$message.success("操作成功");
                this.visible = !this.visible;
              }
            });
          }
        }
      });
    },
    //表单重置
    resetForm() {
      this.roleForm = {};
      this.$refs.roleForm.resetFields();
    },
    //授权
    authorizePerms(record) {
      this.authorizeModal = !this.authorizeModal;
      this.roleId = record.id;
      permsTree().then((res) => {
        if (res.code == 200) {
          this.treeData = res.data;
          rolePermsTree(record.id).then((res) => {
            if (res.code == 200) {
              this.checkedKeys = res.data;
            }
          });
        }
      });
    },

    //授权对话框关闭
    authorizeCancel() {
      this.authorizeModal = !this.authorizeModal;
      this.roleId = undefined;
      this.checkedKeys = [];
    },

    //授权对话框提交
    authorizeSubmit() {
      if (!this.checkedKeys.length > 0) {
        this.$message.error("请赋予该角色基本权限，便于正常使用");
      } else {
        this.authorizeModal = !this.authorizeModal;
        authorize(this.roleId, this.checkedKeys).then((res) => {
          if (res.code == 200) {
            this.$message.success("操作成功");
            this.checkedKeys = [];
          }
        });
      }
    },

    //
    query() {
      this.getRoleList();
    },

    //搜索重置
    reset() {
      this.resetQueryForm();
      this.getRoleList();
    },

    //查询表单重置
    resetQueryForm() {
      (this.searhForm.name = undefined),
        (this.queryForm.roleKey = undefined),
        (this.queryForm.startTime = undefined),
        (this.queryForm.endTime = undefined),
        this.$refs.queryForm.resetFields();
    },
    //日期选择
    onPickerChange(date, dateString) {
      this.queryForm.startTime = dateString[0];
      this.queryForm.endTime = dateString[1];
    },

    remove() {
      this.dataSource = this.dataSource.filter(
        (item) =>
          this.selectedRows.findIndex((row) => row.key === item.key) === -1
      );
      this.selectedRows = [];
    },
    onClear() {
      this.$message.info("您清空了勾选的所有行");
    },
    onStatusTitleClick() {
      this.$message.info("你点击了状态栏表头");
    },
    onChange() {
      this.$message.info("表格状态改变了");
    },
    onSelectChange() {
      this.$message.info("选中行改变了");
    },
    handleMenuClick(e) {
      if (e.key === "delete") {
        this.remove();
      }
    },
  },
};
</script>

<style lang="less" scoped>
.search {
  margin-bottom: 54px;
}
.fold {
  width: calc(100% - 216px);
  display: inline-block;
}
.operator {
  margin-bottom: 18px;
}
@media screen and (max-width: 900px) {
  .fold {
    width: 100%;
  }
}
</style>
