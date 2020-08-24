<template>
  <div>
    <a-modal v-model="visible" title="菜单管理">
      <template slot="footer">
        <a-button key="back" @click="handleCancel">取消</a-button>
        <a-button key="submit" type="primary" :loading="loading" @click="handleOk">提交</a-button>
      </template>
      <a-form-model
        ref="menuForm"
        :model="menuForm"
        :rules="rules"
        :label-col="labelCol"
        :wrapper-col="wrapperCol"
      >
        <a-form-model-item label="名称" prop="name">
          <a-input v-model="menuForm.name" placeholder="请输入菜单名称" />
        </a-form-model-item>
        <a-form-model-item label="菜单类型" prop="menuType">
          <a-radio-group v-model="menuForm.menuType">
            <a-radio value="M">目录</a-radio>
            <a-radio value="C">菜单</a-radio>
            <a-radio value="A">按钮</a-radio>
          </a-radio-group>
        </a-form-model-item>
        <a-form-model-item label="对应组件名称" prop="component">
          <a-input v-model="menuForm.component" placeholder="请输入对应组件名称" />
        </a-form-model-item>
        <a-form-model-item label="地址" prop="path">
          <a-input v-model="menuForm.path" placeholder="菜单访问地址" />
        </a-form-model-item>
        <a-form-model-item label="权限标识" prop="perms">
          <a-input v-model="menuForm.perms" placeholder="请输入权限标识" />
        </a-form-model-item>
        <a-form-model-item label="排序" prop="sort">
          <a-input v-model="menuForm.sort" placeholder="请输入菜单排序" />
        </a-form-model-item>
        <a-form-model-item label="是否可见" prop="visible">
          <a-radio-group v-model="menuForm.visible">
            <a-radio value="1">可见</a-radio>
            <a-radio value="0">不可见</a-radio>
          </a-radio-group>
        </a-form-model-item>
      </a-form-model>
    </a-modal>
    <a-table :columns="columns" :data-source="menuData" :rowKey="record=>record.id">
      <span slot="menuType" slot-scope="menuType">
        <a-tag
          :color="menuType === 'C' ? 'volcano' : menuType === 'M' ? 'geekblue' : 'green'"
        >{{parseMentype(menuType)}}</a-tag>
      </span>
      <span slot="icon" slot-scope="icon">
        <a-icon :type="icon" />
      </span>

      <span slot="action" slot-scope="text, record">
        <a @click="aadMenu(record)">新增</a>
        <a-divider type="vertical" />
        <a @click="edit(record)">修改</a>
        <a-divider type="vertical" />
        <a @click="aadMenu(record)" class="ant-dropdown-link">删除</a>
      </span>
    </a-table>
  </div>
</template>
<script>
import {
  getMenu,
  getMenuById,
  addMenu,
  delMenuByid,
  update,
} from "@/services/systerm/menu";

const columns = [
  {
    title: "名称",
    dataIndex: "name",
    key: "name",
  },
  {
    title: "类型",
    dataIndex: "menuType",
    key: "menuType",
    align: "center",
    scopedSlots: { customRender: "menuType" },
  },
  {
    title: "对应组件名称",
    dataIndex: "component",
    key: "component",
    align: "center",
  },
  {
    title: "路径",
    dataIndex: "path",
    key: "path",
    align: "center",
  },
  {
    title: "权限标识",
    dataIndex: "perms",
    key: "perms",
    align: "center",
  },
  {
    title: "图标",
    dataIndex: "icon",
    key: "icon",
    align: "center",
    scopedSlots: { customRender: "icon" },
  },
  {
    title: "创建时间",
    dataIndex: "createTime",
    key: "createTime",
    align: "center",
  },
  {
    title: "创建人",
    dataIndex: "createBy",
    key: "createBy",
    align: "center",
  },
  {
    title: "操作",
    scopedSlots: { customRender: "action" },
  },
];

export default {
  data() {
    return {
      labelCol: { span: 6 },
      wrapperCol: { span: 14 },
      menuData: [],
      columns,
      loading: false,
      visible: false,
      flag: "add",
      menuForm: {},
      /**
       *
       * 表单校验规则
       */
      rules: {
        name: [{ required: true, message: "请输入名称", trigger: "blur" }],
        component: [
          { required: true, message: "请输入对应组件", trigger: "blur" },
        ],
        sort: [{ required: true, message: "请输入排序", trigger: "blur" }],
        menuType: [
          { required: true, message: "请选择菜单类型", trigger: "blur" },
        ],
        visible: [
          { required: true, message: "请选择是否可见", trigger: "blur" },
        ],
        path: [
          { required: true, message: "请填写菜单访问地址", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.initTable();
  },
  methods: {
    initTable() {
      getMenu().then((res) => {
        if (res.code == 200) {
          this.menuData = res.data;
        }
      });
    },
    parseMentype(flag) {
      var name = "";
      if (flag === "C") {
        name = "菜单";
      } else {
        name = flag === "M" ? "目录" : "按钮";
      }
      return name;
    },
    //新增菜单
    aadMenu(menu) {
      this.menuForm.pid = menu.id;
      this.flag = "add";
      this.visible = true;
    },
    //修改
    edit(menu) {
      this.flag = "edit";
      this.visible = true;
      var menuId = menu.id;
      getMenuById(menuId).then((res) => {
        if (res.code == 200) {
          this.menuForm = res.data;
        }
      });
    },

    handleOk(e) {
      this.$refs.menuForm.validate((valid) => {
        if (valid) {
          if (this.flag == "add") {
            addMenu(this.menuForm).then((res) => {
              if (res.code == 200) {
                this.initTable();
                this.$message.success("操作成功");
                this.resetForm();
                this.visible=false
              } else {
                this.$message.error(res.msg);
              
              }
            });
          } else {
            update(this.menuForm).then((res) => {
              if (res.code == 200) {
                this.visible = false;
                this.initTable();
                this.$message.success("操作成功");
                this.resetForm();
              }
            });
          }
        } else {
          return false;
        }
      });
    },
    handleCancel(e) {
      this.visible = false;
      this.resetForm();
    },
    //表单重置
    resetForm() {
      this.menuForm = {
        id: undefined,
        name: undefined,
        component: undefined,
        pid: undefined,
        sort: undefined,
        icon: undefined,
        path: undefined,
        menuType: undefined,
        visible: undefined,
        perms: undefined,
      };
      this.$refs.menuForm.resetFields();
    },
  },
};
</script>