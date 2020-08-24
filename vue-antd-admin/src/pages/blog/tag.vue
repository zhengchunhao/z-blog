<template>
  <div class="card-list">
    <a title="快速开始" />
    <a-list
      :grid="{gutter: 24, lg: 3, md: 2, sm: 1, xs: 1}"
      :dataSource="dataSource"
    >
      <a-list-item slot="renderItem" slot-scope="item">
        <template v-if="item.add">
          <a-button class="new-btn" type="dashed">
            <a-icon type="plus" />新增产品
          </a-button>
        </template>
        <template v-else>
          <a-card :hoverable="true">
           <a-card-meta @click="tagDetail(item)">
              <div style="margin-bottom: 3px" slot="title">{{item.title}}</div>
              <a-avatar
                class="card-avatar"
                slot="avatar"
                :src="'data:image/gif;base64,'+item.avatar"
                size="large"
              />
              <div class="meta-content" slot="description">{{item.content}}</div>
            </a-card-meta>
            <a slot="actions" @click="edit(item)">修改</a>
            <a slot="actions">删除</a>
          </a-card>
        </template>
      </a-list-item>
      <a-modal title="标签管理" :visible="visible" @ok="handleSubmit" @cancel="handleCancel">
      <a-form-model
        :model="tagForm"
        ref="tagForm"
        :rules="rules"
        :label-col="labelCol"
        :wrapper-col="wrapperCol"
      >
        <a-form-model-item label="名称" prop="tagName">
          <a-input v-model="tagForm.tagName" />
        </a-form-model-item>
        <a-form-model-item label="标签头像">
          <a-upload
            :customRequest="customRequest"
            :default-file-list="imageList"
            :remove="removeImage"
            list-type="picture"
            class="upload-list-inline"
          >
            <div>
              <a-button :disabled="disabled">
                <a-icon type="upload" />上传
              </a-button>
            </div>
          </a-upload>
        </a-form-model-item>
        <a-form-model-item label="标签介绍" prop="tagIntroduce">
          <a-input v-model="tagForm.tagIntroduce" type="textarea" />
        </a-form-model-item>
      </a-form-model>
    </a-modal>
    </a-list>
  </div>
</template>

<script>
import {
  upload,
  image,
  getAllTags,
  updateTag,
  addTag,
  tag,
  delImage,
} from "@/services/blog/tag";

export default {
  name: 'CardList',
  data () {
    return {
      labelCol: { span: 4 },
      wrapperCol: { span: 14 },
      dataSource: [],
      tagForm: {},
      visible: false,
      imageList: [],
      disabled: false,
      rules: {
        tagName: [{ required: true, message: "请输入名称", trigger: "blur" }],
        tagIntroduce: [
          { required: true, message: "请输入标签介绍", trigger: "blur" },
        ],
      },
      desc: '一起撰写我们的播客吧！！！',
      linkList: [
        {icon: 'rocket', href:{path:'/blog/myblog'}  , title: '快速开始'},
        {icon: 'info-circle-o', href: '/#/', title: '产品简介'},
        {icon: 'file-text', href: '/#/', title: '产品文档'}
      ],
    }
  },
  created() {
    this.getList();
  },

  methods: {
    //获取列表
    getList() {
      getAllTags().then((res) => {
        this.dataSource = [];
        if (res.code == 200) {
          this.dataSource.push({
            add: true,
          });
          res.data.forEach((element) => {
            this.dataSource.push({
              id: element.tagId,
              title: element.tagName,
              avatar: element.imagePath,
              content: element.tagIntroduce,
            });
          });
        }
      });
    },

    //表单提交
    handleSubmit() {
      this.$refs.tagForm.validate((valid) => {
        if (valid) {
          if (this.tagForm.tagId) {
            updateTag(this.tagForm).then((res) => {
              if (res.code == 200) {
                this.$message.success("操作成功");
                this.openOrCloseModal();
                this.resetForm();
                this.getList();
              }
            });
          } else {
            addTag(this.tagForm).then((res) => {
              if (res.code == 200) {
                this.$message.success("操作成功");
                this.openOrCloseModal();
                this.resetForm();
                this.getList();
              }
            });
          }
        }
      });
    },
    //修改
    edit(item) {
      this.openOrCloseModal();
      tag(item.id).then((res) => {
        if (res.code == 200) {
          this.tagForm = res.data;
          image(this.tagForm.imageId).then((res) => {
            if (res) {
              this.disabled = true;
            }
            var data = res;
            var image = {
              uid: data.id,
              name: data.imageName,
              status: "done",
              url: "data:image/gif;base64," + data.path,
            };
            this.imageList.push(image);
          });
        }
      });
    },
    //对话框取消
    handleCancel() {
      this.visible = !this.visible;
      this.resetForm();
      this.imageList = [];
      if (this.tagForm.imageId) {
        this.removeImage();
      }
    },
    //表单重置
    resetForm() {
      this.tagForm = {};
      this.$refs.tagForm.resetFields();
    },
    openOrCloseModal() {
      this.visible = !this.visible;
    },

    //自定义上传
    customRequest(data) {
      const formData = new FormData();
      formData.append("file", data.file);
      upload(formData).then((res) => {
        if (res.code == 200) {
          this.$message.success("上传成功");
          this.tagForm.imageId = res.data;
          data.onSuccess();
          this.disabled = true;
        } else {
          this.$message.error(res.msg);
          data.onError();
        }
      });
    },
    //图片移除
    removeImage(data) {
      this.imageList = [];

      this.disabled = false;
      delImage(this.tagForm.imageId).then((res) => {
        if (res.code == 200) {
          this.tagForm.imageId = undefined;
          this.$message.success("删除成功");
        } else {
          this.$message.error("删除失败");
        }
      });
    },
    //标签下得文章
    tagDetail(item){
      this.$router.push({path:'/blog/myblog',query:{tagId:item.id}})
    },
      //链接路由跳转
  goLink(item){
     this.$rouer.push(item)
  }
  },
}
</script>

<style lang="less" scoped>
  .card-avatar {
    width: 48px;
    height: 48px;
    border-radius: 48px;
  }
  .new-btn{
    border-radius: 2px;
    width: 100%;
    height: 187px;
  }
  .meta-content{
    position: relative;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    height: 64px;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
  }

</style>
