<template>
  <div>
    <a-form-model
      style="max-width: 500px; margin: 40px auto 0;"
      :model="blog"
       ref="blog"
      :rules="rules"
    >
      <a-form-model-item label="标题" prop="blogTitle" :labelCol="{span: 7}" :wrapperCol="{span: 17}">
        <a-input v-model="blog.blogTitle" placeholder="请输入博文标题" />
      </a-form-model-item>
      <a-form-model-item label="标签" prop="tagIds" :labelCol="{span: 7}" :wrapperCol="{span: 17}">
        <a-select
          mode="multiple"
          style="width: 100%"
          placeholder="请选择博客标签"
          v-model="blog.tagIds"
          @change="handleSelectChange"
        >
          <a-select-option
            v-for="(item,index) in tagSelectOptions"
            :value="item.tagId"
            :key="index"
          >{{item.tagName}}</a-select-option>
        </a-select>
      </a-form-model-item>

      <a-form-model-item
        label="介绍"
        prop="introduce"
        :labelCol="{span: 7}"
        :wrapperCol="{span: 17}"
      >
        <a-input v-model="blog.introduce" type="textarea" />
      </a-form-model-item>

      <a-form-model-item :wrapperCol="{span: 17, offset: 7}">
        <a-button type="primary" @click="nextStep">下一步</a-button>
    </a-form-model-item>
    </a-form-model>
  
  </div>
</template>

<script>
import { getAllTags } from "@/services/blog/tag";
export default {
  name: "Step1",
  props:{
    blogdata:{
     type:Object,
    }
  },
  data() {
    return {
      blog: {},
      tagSelectOptions: [],
     rules: {
        blogTitle: [{ required: true, message: "请输入标题", trigger: "blur" }],
        tagIds: [
          { required: true, message: "请选择标签", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getTags();
   this.blog= this.blogdata
  },
  methods: {
    nextStep() {
       this.$refs.blog.validate((valid) => {
       if(valid){
         this.$emit("nextStep",this.blog);
       }
       })
     
    },
    handleSelectChange() {
      console.log(this.blog);
    },
    getTags() {
      getAllTags().then((res) => {
        if (res.code == 200) {
          this.tagSelectOptions = res.data;
        }
      });
    },
  },
};
</script>

<style scoped>
</style>
