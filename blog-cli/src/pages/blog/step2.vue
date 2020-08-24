<template>
  <div>
  <a-divider style="margin-bottom: 32px"/>
  <mavon-editor class="myMavon" v-model="blog.blogBody" ref=md
 @imgAdd="handleEditorImgAdd"
  @imgDel="handleEditorImgDel"
  @save="handleSave"
      />
         <a-button :loading="loading" type="primary" @click="nextStep">{{'发布文章'}}</a-button>
        <a-button style="margin-left: 8px" @click="prevStep">{{"上一步"}}</a-button>
  </div>
</template>

<script>
import {upload,deleteFile} from "@/services/blog/write";
const BASE_URL = process.env.NODE_ENV === 'production' ?  process.env.VUE_APP_API_BASE_URL:process.env.VUE_APP_API_BASE_URL 
export default {
  name: 'Step2',
  data () {
    return {
      loading: false,
      blog:{}
    }
  },
  methods: {
    nextStep () {
    if(!this.blog.blogBody) {
      this.$message.error('请撰写博文')
    }else{
     this.$emit('nextStep',this.blog) 
    }
    },
    prevStep () {
      this.$emit('prevStep')
    },
       //图片上传
   handleEditorImgAdd(pos, file){  
   var formdata = new FormData();
   formdata.append('file', file);
   upload(formdata).then(res=>{
       if(res.code==200){
       this.$refs.md.$img2Url(pos, BASE_URL+"/commom/download?imgId="+res.data)
       }
   })
   },
   //图片删除
   handleEditorImgDel(pos){
    console.log(pos)
    var str=pos[0].split("=")
    deleteFile(str[1]).then(res=>{
      if(res.code==200){
        this.$message.success('删除成功')
      }
    }) 
   },
   handleSave(){
     this.$emit('preservation',this.blog.blogBody)
   },
  }
}
</script>

<style lang="less" scoped>
  .stepFormText {
    margin-bottom: 24px;
    :global {
      .ant-form-item-label,
      .ant-form-item-control {
        line-height: 22px;
      }
    }
  }
  .myMavon {
  height: 700px;
}
</style>
