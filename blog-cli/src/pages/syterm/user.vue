<template>
  <div>
    <a-card>
      <div :class="advanced ? 'search' : null">
        <a-form layout="horizontal">
          <div :class="advanced ? null: 'fold'">
            <a-row>
              <a-col :md="8" :sm="24">
                <a-form-model-item
                  label="姓名"
                  :labelCol="{span: 5}"
                  :wrapperCol="{span: 18, offset: 1}"
                >
                  <a-input placeholder="请输入用户名" v-model="searchForm.username" />
                </a-form-model-item>
              </a-col>
              <a-col :md="8" :sm="24">
                <a-form-model-item
                  label="手机号"
                  :labelCol="{span: 5}"
                  :wrapperCol="{span: 18, offset: 1}"
                >
                  <a-input placeholder="请输入手机号" v-model="searchForm.phone" />
                </a-form-model-item>
              </a-col>
              <a-col :md="8" :sm="24">
                <a-form-model-item
                  label="邮箱地址"
                  :labelCol="{span: 5}"
                  :wrapperCol="{span: 18, offset: 1}"
                >
                  <a-input placeholder="请输入邮箱地址" v-model="searchForm.email" />
                </a-form-model-item>
              </a-col>
            </a-row>
            <a-row v-if="advanced">
              <a-col :md="8" :sm="24">
                <a-form-model-item
                  label="性别"
                  :labelCol="{span: 5}"
                  :wrapperCol="{span: 18, offset: 1}"
                >
                  <a-select placeholder="请选择" v-model="searchForm.sex">
                    <a-select-option value="M">男</a-select-option>
                    <a-select-option value="W">女</a-select-option>
                  </a-select>
                </a-form-model-item>
              </a-col>
              <a-col :md="8" :sm="24">
                <a-form-model-item
                  label="状态"
                  :labelCol="{span: 5}"
                  :wrapperCol="{span: 18, offset: 1}"
                >
                  <a-select placeholder="请选择" v-model="searchForm.enabled">
                    <a-select-option value="T">启用</a-select-option>
                    <a-select-option value="F">禁用</a-select-option>
                  </a-select>
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
            <a-button type="primary" @click="search">查询</a-button>
            <a-button style="margin-left: 8px" @click="reset">重置</a-button>
            <a @click="toggleAdvanced" style="margin-left: 8px">
              {{advanced ? '收起' : '展开'}}
              <a-icon :type="advanced ? 'up' : 'down'" />
            </a>
          </span>
        </a-form>
      </div>

      <div>
        <div class="operator">
          <a-button @click="openModal" type="primary">新增</a-button>
          <a-button>批量操作</a-button>
          <a-dropdown>
            <a-menu slot="overlay">
              <a-menu-item key="delete">
                <a-popconfirm
                  placement="top"
                  :disabled="this.selectedRows!== undefined && this.selectedRows!==null&&this.selectedRows.length>0 ? false:true"
                  ok-text="确定"
                  cancel-text="取消"
                  @confirm="confirm"
                >
                  <template slot="title">
                    <p>{{ '是否确定要删除' }}</p>
                  </template>
                  <a-button type="link" @click="remove(-1)">删除</a-button>
                </a-popconfirm>
              </a-menu-item>
              <a-menu-item key="audit">审批</a-menu-item>
            </a-menu>
            <a-button>
              更多操作
              <a-icon type="down" />
            </a-button>
          </a-dropdown>
        </div>
        <standard-table
          :columns="columns"
          :dataSource="dataSource"
          :selectedRows.sync="selectedRows"
          :pagination="pagination"
          :rowKey="record=> record.id"
          @change="onChange"
        >
          <div slot="sex" slot-scope="{record}">
            <a-tag :color="record.sex=='M' ? 'geekblue' : 'green'">{{record.sex==='M'?'男':'女'}}</a-tag>
          </div>
          <div slot="enabled" slot-scope="{record}">
            <a-tag :color="record.enabled=='T' ? 'green' : 'red'">{{record.enabled==='T'?'启用':'禁用'}}</a-tag>
          </div>
          <div slot="createTime" slot-scope="{record}">
            <span>{{record.createTime==null? '': moment(record.createTime).format('YYYY-MM-DD')}}</span>
          </div>
            <div slot="editTime" slot-scope="{record}">
            <span>{{record.editTime==null? '': moment(record.editTime).format('YYYY-MM-DD')}}</span>
          </div>

          <div slot="action" slot-scope="{record}">
            <a style="margin-right: 8px" @click="openModal(record)">
              <a-icon type="edit" />编辑
            </a>
            <a @click="remove(record.id)">
              <a-icon type="delete" />删除
            </a>
          </div>
          <template slot="statusTitle">
            <a-icon @click.native="onStatusTitleClick" type="info-circle" />
          </template>
        </standard-table>
      </div>
    </a-card>
    <a-modal v-model="visible" title="用户管理" @ok="handleOk" @cancel="handleCancel">
      <a-form-model
        ref="userForm"
        :model="userForm"
        :rules="rules"
        :label-col="labelCol"
        :wrapper-col="wrapperCol"
      >
        <a-form-model-item label="用户名称" has-feedback prop="username">
          <a-input v-model="userForm.username" placeholder="请输入用户名称" />
        </a-form-model-item>
        <a-form-model-item has-feedback label="手机号" prop="phone">
          <a-input v-model="userForm.phone" placeholder="请输入用户手机号" />
        </a-form-model-item>
        <a-form-model-item label="邮箱" has-feedback prop="email">
          <a-input v-model="userForm.email" placeholder="请输入邮箱地址" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="roleIds" label="角色">
          <a-select
            v-model="userForm.roleIds"
            placeholder="请选择角色"
            mode="multiple"
            option-label-prop="label"
          >
            <a-select-option
              v-for="(item) in roleOptions"
              :key="item.id"
              :value="item.id"
              :label="item.name"
            >{{item.name}}</a-select-option>
          </a-select>
        </a-form-model-item>
        <a-form-model-item has-feedback label="密码" prop="password">
          <a-input v-model="userForm.password" type="password" placeholder="请输入密码" />
        </a-form-model-item>
        <a-form-model-item has-feedback label="重复密码" prop="checkpassword">
          <a-input v-model="userForm.checkpassword" type="password" placeholder="请再次输入密码" />
        </a-form-model-item>
        <a-form-model-item label="性别" prop="sex">
          <a-radio-group v-model="userForm.sex">
            <a-radio value="M">男</a-radio>
            <a-radio value="W">女</a-radio>
          </a-radio-group>
        </a-form-model-item>
      </a-form-model>
    </a-modal>
  </div>
</template>

<script>
import StandardTable from "@/components/table/StandardTable";
import moment from "moment";
import {
  users,
  addUser,
  updateUser,
  queryUserById,
  roleOptions,
  userHaveRoleIds,
  CheckUserNameUnique,
  CheckPhoneUnique,
  CheckEmailUnique,
  deleteUsers,
} from "@/services/user";

const columns = [
  {
    title: "用户名",
    dataIndex: "username",
    align: "center",
  },
  {
    title: "手机号",
    dataIndex: "phone",
    align: "center",
  },
  {
    title: "邮箱地址",
    dataIndex: "email",
    align: "center",
  },
  {
    title: "性别",
    dataIndex: "sex",
    align: "center",
    scopedSlots: { customRender: "sex" },
  },
  {
    title: "状态",
    dataIndex: "enabled",
    align: "center",
    scopedSlots: { customRender: "enabled" },
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
    scopedSlots: { customRender: "createTime" },
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
    scopedSlots: { customRender: "editTime" },
  },

  {
    title: "操作",
    align: "center",
    scopedSlots: { customRender: "action" },
  },
];
export default {
  name: "User",
  components: { StandardTable },
  data() {
    let validatePass = (rule, value, callback) => {
      if (value === "" || value == undefined) {
        callback(new Error("请填写密码"));
      } else if (value.length < 6) {
        callback(new Error("密码不小于三个字符"));
      } else {
        if (
          this.userForm.password !== "" &&
          this.userForm.password != undefined
        ) {
          this.$refs.userForm.validateField("checkPass");
        }
        callback();
      }
    };
    let validatePass2 = (rule, value, callback) => {
      if (value === "" || value == undefined) {
        callback(new Error("请再一次填写密码"));
      } else if (value !== this.userForm.password) {
        callback(new Error("两次密码不一样"));
      } else {
        callback();
      }
    };

    let checkUserName = (rule, value, callback) => {
      if (value === "" || value == undefined) {
        callback(new Error("请填写用户名"));
      } else if (value.length < 3) {
        callback(new Error("用户名不能少于三个字符"));
      } else if (!value.length < 3) {
        var params = {
          userName: value,
          id: this.userForm.id,
        };
        CheckUserNameUnique(params).then((res) => {
          if (!res) {
            callback(new Error("此用户名已被使用"));
          } else {
            callback();
          }
        });
      } else {
        callback();
      }
    };
    let checkPhone = (rule, value, callback) => {
      if (value === "" || value == undefined) {
        callback(new Error("请填写手机号"));
      } else if (value.length > 0) {
        const phoneRule = /^1[3|4|5|7|8][0-9]\d{8}$/;
        if (!phoneRule.test(value)) {
          callback(new Error("请输入正确手机号"));
        } else if (phoneRule.test(value)) {
          var params = {
            phone: value,
            id: this.userForm.id,
          };
          CheckPhoneUnique(params).then((res) => {
            if (!res) {
              callback(new Error("该手机号已被使用"));
            } else {
              callback();
            }
          });
        } else {
          callback();
        }
      } else {
        callback();
      }
    };
    let checkEmail = (rule, value, callback) => {
      if (value === "" || value == undefined) {
        callback(new Error("请填写邮箱地址"));
      } else if (value.length > 0) {
        const emailRule = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
        if (!emailRule.test(value)) {
          callback(new Error("请输入正确邮箱地址"));
        } else if (emailRule.test(value)) {
          var params = {
            email: value,
            id: this.userForm.id,
          };
          CheckEmailUnique(params).then((res) => {
            if (!res) {
              callback(new Error("该邮箱地址已被使用"));
            } else {
              callback();
            }
          });
        } else {
          callback();
        }
      } else {
        callback();
      }
    };
    return {
      advanced: false,
      labelCol: { span: 6 },
      wrapperCol: { span: 14 },
      columns,
      moment,
      dataSource: [],
      selectedRows: [],
      visible: false,
      loading: false,
      roleOptions: [],
      userForm: {},
      searchForm: {
        username: undefined,
        email: undefined,
        phone: undefined,
        startTime: undefined,
        endTime: undefined,
        enabled: undefined,
        sex: undefined,
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
        username: [{ required: true, validator: checkUserName }],
        password: [
          { required: true, validator: validatePass, trigger: "blur" },
        ],
        checkpassword: [
          { required: true, validator: validatePass2, trigger: "blur" },
        ],
        phone: [{ required: true, validator: checkPhone }],
        email: [{ required: true, validator: checkEmail }],
        sex: [{ required: true, message: "请选择性别", trigger: "change" }],
        roleIds: [{ required: true, message: "请选择角色", trigger: "blur" }],
      },
    };
  },

  created() {
    this.initTable();
    this.getRoleOptions();
  },
  methods: {
    initTable() {
      users(this.searchForm).then((res) => {
        if (res.code == 200) {
          this.dataSource = res.rows;
          this.pagination.total = res.total;
        }
      });
    },
    //分页操作
    onChange(page) {
      this.searchForm.pageSize = page.pageSize;
      this.searchForm.pageNum = page.current;
      this.initTable();
    },
    toggleAdvanced() {
      this.advanced = !this.advanced;
    },

    //取消对话框
    handleCancel() {
      this.openOrCloseModal();
      this.resetForm();
    },
    //打开对话框
    openModal(record) {
      this.openOrCloseModal();
      if (record.id) {
        queryUserById(record.id).then((res) => {
          if (res.code == 200) {
             userHaveRoleIds(record.id).then(res=>{
               if(res.code==200){
                 this.userForm.roleIds=res.data
               }
             })
            this.userForm = res.data;
            this.userForm.checkpassword=this.userForm.password
          }
        });
      }
    },
    //表单提交
    handleOk() {
      this.$refs.userForm.validate((valid) => {
        if (valid) {
          this.loading = true;
          if (this.userForm.id) {
            console.log(this.userForm)
            updateUser(this.userForm).then((res) => {
              if (res.code == 200) {
                this.$message.success("编辑成功");
                this.initTable();
                this.resetForm();
              } else {
                this.$message.error(res.msg);
              }
            });
          } else {
            addUser(this.userForm).then((res) => {
              if (res.code == 200) {
                this.$message.success("新增成功");
                this.initTable();
                this.resetForm();
              } else {
                this.$message.error(res.msg);
              }
            });
          }

          this.openOrCloseModal();
          this.loading = false;
        }
      });
    },
    //表单重置
    resetForm() {
      this.userForm = {};
      this.$refs.userForm.resetFields();
    },
    //删除
    remove(id) {
      if (id == -1) {
        if (
          this.selectedRows == null ||
          this.selectedRows == undefined ||
          !this.selectedRows.length > 0
        ) {
          this.$message.error("请至少选择一条数据");
        }
      } else {
        var ids = [id];
        deleteUsers(ids).then((res) => {
          if (res.code == 200) {
            this.$message.success("删除成功");
            this.initTable();
          }
        });
      }
    },
    //确认删除
    confirm() {
      var ids = [];
      this.selectedRows.forEach((element) => {
        ids.push(element.id);
      });
      deleteUsers(ids).then((res) => {
        if (res.code == 200) {
          this.$message.success("删除成功");
          this.selectedRows = [];
          this.initTable();
        }
      });
    },

    //控制对话框
    openOrCloseModal() {
      this.visible = !this.visible;
    },
    //搜索
    search() {
      this.initTable();
    },
    //搜索表单重置
    resetSearchForm() {
      this.searchForm.username = undefined;
      this.searchForm.phone = undefined;
      this.searchForm.email = undefined;
      this.searchForm.startTime = undefined;
      this.searchForm.endTime = undefined;
      this.searchForm.enabled = undefined;
      this.searchForm.sex = undefined;
    },

    //日期选择
    onPickerChange(date, dateString) {
      this.searchForm.startTime = dateString[0];
      this.searchForm.endTime = dateString[1];
    },
    //重置按钮
    reset() {
      this.resetSearchForm();
      this.initTable();
    },
    getRoleOptions() {
      roleOptions().then((res) => {
        if (res.code == 200) {
          this.roleOptions = res.data;
        }
      });
    },
  },
};
</script>

<style>
</style>
    