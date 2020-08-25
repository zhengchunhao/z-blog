<template>
  <div>
    <a-card :bordered="false">
      <detail-list v-if="tagId" title="博文标签">
        <detail-list-item term="标签名字">{{tag.tagName}}</detail-list-item>
        <detail-list-item term="博文数量">{{tag.blogCount}}</detail-list-item>
        <detail-list-item
          term="创建时间"
        >{{tag.createTime==null?'-':moment(tag.createTime).format('YYYY-MM-DD')}}</detail-list-item>
        <detail-list-item term="标签描述">{{tag.tagIntroduce}}</detail-list-item>
      </detail-list>
    </a-card>
    <a-card :bordered="false">
      <a-steps class="steps" :current="current">
        <a-step title="博文基本信息" />
        <a-step title="博文撰写" />
        <a-step title="发表" />
      </a-steps>
      <div class="content">
        <step1 v-if="current === 0" :blogdata.sync="blog" @nextStep="nextStep"></step1>
        <step2
          v-if="current === 1"
          @nextStep="nextStep"
          @prevStep="prevStep"
          @preservation="preservation"
        ></step2>
        <step3 v-if="current === 2" @prevStep="prevStep" @finish="finish"></step3>
      </div>
    </a-card>
  </div>
</template>

<script>
import DetailList from "../../components/tool/DetailList";
import { tag } from "@/services/blog/tag";
import { add, update, blog } from "@/services/blog/write";
import Step1 from "./step1";
import Step2 from "./step2";
import Step3 from "./step3";
import moment from "moment";
const DetailListItem = DetailList.Item;

export default {
  name: "BasicDetail",
  components: { DetailListItem, DetailList, Step1, Step2, Step3 },
  data() {
    return {
      moment,
      tagId: undefined,
      tag: {},
      blog: {},
      current: 0,
    };
  },
  created() {
    this.tagId=this.$route.query.tagId
    if(!this.tagId==null||this.tagId==undefined){
    this.getTag();
    }
    if(this.$route.query.blogId!=null&&this.$route.query.blogId!=undefined){
     blog(this.$route.query.blogId).then(res=>{
      if(res.code==200){
        this.blog=res.data
      } 
     }) 
    }
  },
  methods: {
    getTag() {
      tag(this.tagId).then((res) => {
        if (res.code == 200) {
          this.tag = res.data;
        }
      });
    },

    nextStep(blog) {
      if (this.current < 2) {
        if (this.current === 0) {
          this.blog.blogTitle = blog.blogTitle;
          this.blog.tagIds = blog.tagIds;
          this.blog.introduce = blog.introduce;
          this.current += 1;
        } else {
          this.blog.blogBody = blog.blogBody;
          if (this.blog.blogId == undefined || this.blog.blogId == null) {
            this.addBlog(this.blog);
          } else {
            this.updateBlog(this.blog);
          }
        }
      }
    },
    prevStep() {
      if (this.current > 0) {
        this.current -= 1;
      }
    },
    //保存
    preservation(blogBody) {
      console.log("保存")
      console.log(blogBody)
      this.blog.blogBody = blogBody;
      if(this.blog.blogId == undefined || this.blog.blogId == null){
        add(this.blog).then(res=>{
         if(res.code==200){
           this.blog=res.data
          this.$message.success("已经成功同步到服务器")
         }   
        })
      }else{
        update(this.blog).then(res=>{
          if(res.code==200){
           this.blog=res.data
           this.$message.success("已经成功同步到服务器") 
          }
        })
      }
    },

    finish() {
      this.current = 0;
      this.blog = {};
    },
    addBlog() {
      add(this.blog).then((res) => {
        if (res.code == 200) {
          if (res.data) {
            this.blog = res.data;
            this.current += 1;
          }
        } else {
          this.$message.error("网络错误，请重试");
        }
      });
    },
    updateBlog() {
     update(this.blog).then(res=>{
       if(res.code==200){
         this.blog=res.data
         this.$message.success('更新成功')
         this.current+=1
       }
     })     
    },
  },
};
</script>

<style lang="less" scoped>
.title {
  color: @title-color;
  font-size: 16px;
  font-weight: 500;
  margin-bottom: 16px;
}
</style>
