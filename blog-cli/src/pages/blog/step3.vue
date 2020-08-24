<template>
  <div style="max-width: 500px; margin: 40px auto 0; text-align: center">
    <result :title="'发布成功'" :is-success="true" />
    <a-button type="primary" @click="doOnceAgin">{{'再写一篇'}}</a-button>
    <a-button style="margin-left: 8px" @click="tagList">{{'返回标签页'}}</a-button>
  </div>
</template>

<script>
import Result from '@/components/result/Result'
export default {
  name: 'Step3',
  components: {Result},
  methods: {
    doOnceAgin () {
      this.$emit('finish')
    },
    tagList(){
     this.$router.push({path:'/blog/tag'})   
    }
  }
}
</script>

<style scoped>

</style>
