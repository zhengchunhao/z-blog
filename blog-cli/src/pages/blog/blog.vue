<template>
  <div>
    <a-card :bordered="false">
      <div style="display: flex; flex-wrap: wrap">
        <head-info title="标签名称" content="8个任务" :bordered="true" />
        <head-info title="博文总数" content="32分钟" :bordered="true" />
        <head-info title="图发布博文数" content="24个" />
      </div>
    </a-card>
    <a-card style="margin-top: 24px" :bordered="false" title="标准列表">
      <div slot="extra">
        <a-radio-group>
          <a-radio-button>全部</a-radio-button>
          <a-radio-button>已发布</a-radio-button>
          <a-radio-button>在进行</a-radio-button>
          <a-radio-button>已删除</a-radio-button>
        </a-radio-group>
        <a-input-search style="margin-left: 16px; width: 272px;" />
      </div>
      <a-button type="dashed" style="width: 100%" icon="plus" @click="addBlog">添加</a-button>
      <a-list item-layout="horizontal" :data-source="dataSource" :pagination="pagination">
        <a-list-item slot="renderItem" slot-scope="item">
          <a-list-item-meta :description="item.introduce">
            <a slot="title">{{ item.blogTitle }}</a>
            <a-avatar slot="avatar" :src="'data:image/gif;base64,'+tag.avatar" />
          </a-list-item-meta>
             <div slot="actions">
              <a>编辑</a>
            </div>
            <div slot="actions">
              <a-dropdown>
                <a-menu slot="overlay">
                  <a-menu-item>
                    <a>编辑</a>
                  </a-menu-item>
                  <a-menu-item>
                    <a>删除</a>
                  </a-menu-item>
                </a-menu>
                <a>
                  更多
                  <a-icon type="down" />
                </a>
              </a-dropdown>
            </div>
             <div class="list-content">
            <div class="list-content-item">
              <a-tag v-if="item.blogState" color="green">{{'发布'}}</a-tag>
              <a-tag v-else :color="item.blogState==0 ? 'red': 'orange'">{{item.blogState==0?'删除':'进行中'}}</a-tag>
            </div>
            <div class="list-content-item">
              <span>开始时间</span>
              <p>{{moment(item.createTime).format('YYYY-MM-DD')}}</p>
            </div>
            <div class="list-content-item">
              <a-progress :percent="80" style="width: 180px" />
            </div>
          </div>
        </a-list-item>
      </a-list>
    </a-card>
  </div>
</template>

<script>
import HeadInfo from "../../components/tool/HeadInfo";
import { blogs } from "@/services/blog/blog";
import moment from "moment";
export default {
  name: "StandardList",
  components: { HeadInfo },
  data() {
    return {
      tag: this.$route.query.tag,
      dataSource: [],
      moment,
      queryForm: {
        blogState: undefined,
        blogTitle: undefined,
        pageSize: 10,
        pageNum: 1,
      },
      pagination: {
        pageSize: 10,
        current: 1,
        total: 0,
        showSizeChanger: true,
        showTotal: (total) => `总共 ${total} 条记录`,
        onShowSizeChange: (current, pageSize) =>
          this.onChange(current, pageSize),
        onChange: (page, pageSize) => this.onChange(page, pageSize),
      },
    };
  },
  created() {
    this.getBlogList();
  },
  methods: {
    addBlog() {
      this.$router.push({ path: "/blog/write", query: { tagId: this.tagId } });
    },
    //分页操作
    onChange(page, pageSize) {
      this.queryForm.pageNum = page;
      this.queryForm.pageSize = pageSize;
      this.pagination.current = page;
      this.pagination.pageSize = pageSize;
      this.getBlogList();
    },
    //获取博客列表
    getBlogList() {
      blogs(this.queryForm, this.tag.id).then((res) => {
        if (res.code == 200) {
          this.pagination.total = res.total;
          this.dataSource = res.rows;
        }
      });
    },
    //编辑
    edit(item) {
      console.log("编辑");
    },
    del(item) {
      console.lgo("删除");
    },
  },
};
</script>
<style lang="less" scoped>
.list-content-item {
  color: @text-color-second;
  display: inline-block;
  vertical-align: middle;
  font-size: 14px;
  margin-left: 40px;
  span {
    line-height: 20px;
  }
  p {
    margin: 4px 0 0;
    line-height: 22px;
  }
}
.mymavon {
  height: 1000px;
}
</style>
