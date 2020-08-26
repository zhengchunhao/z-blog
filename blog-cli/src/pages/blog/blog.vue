<template>
  <div>
    <a-card :bordered="false">
      <div style="display: flex; flex-wrap: wrap">
        <head-info title="标签名称" content="8个任务" :bordered="true" />
        <head-info title="博文总数" content="32分钟" :bordered="true" />
        <head-info v-if="queryForm.blogState==0" title="已删除" :content="pagination.total" />
        <head-info v-else-if="queryForm.blogState==2" title="未完成" :content="pagination.total" />
        <head-info v-else title="已发布" :content="pagination.total" />
      </div>
    </a-card>
    <a-card style="margin-top: 24px" :bordered="false" title="标准列表">
      <div slot="extra">
        <a-radio-group>
          <a-radio-button @click="searchState()">全部</a-radio-button>
          <a-radio-button @click="searchState(1)">已发布</a-radio-button>
          <a-radio-button @click="searchState(2)">在进行</a-radio-button>
          <a-radio-button @click="searchState(0)">已删除</a-radio-button>
        </a-radio-group>
        <a-input-search
          style="margin-left: 16px; width: 272px;"
          v-model="queryForm.blogTitle"
          @search="onSearch"
        />
      </div>
      <a-button type="dashed" style="width: 100%" icon="plus" @click="addBlog">添加</a-button>
      <a-list item-layout="horizontal" :data-source="dataSource" :pagination="pagination">
        <a-list-item slot="renderItem" slot-scope="item">
          <a-list-item-meta :description="item.introduce">
            <a slot="title">{{ item.blogTitle }}</a>
            <a-avatar slot="avatar" :src="'data:image/gif;base64,'+tag.avatar" />
          </a-list-item-meta>
          <div slot="actions">
            <a>进度提交</a>
          </div>
          <div slot="actions">
            <a-dropdown>
              <a-menu slot="overlay">
                <a-menu-item @click="edit(item)">
                  <a>编辑</a>
                </a-menu-item>

                <a-menu-item>
                  <a-popconfirm
                    title="确定要删除这篇文章吗?"
                    ok-text="确定"
                    cancel-text="取消"
                    @confirm="confirm(item)"
                    @cancel="cancel"
                  >
                    <a>删除</a>
                  </a-popconfirm>
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
              <a-tag
                v-else
                :color="item.blogState==0 ? 'red': 'orange'"
              >{{item.blogState==0?'删除':'进行中'}}</a-tag>
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
import { blogs, delBlog } from "@/services/blog/blog";
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
      this.$router.push({ path: "/blog/write", query: { tagId: this.tag.id } });
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
      this.$router.push({
        path: "/blog/write",
        query: { blogId: item.blogId, tagId: this.tag.id },
      });
    },

    searchState(state) {
      if (state) {
        this.queryForm.blogState = state;
        this.getBlogList();
      } else {
        this.queryForm.blogState = undefined;
        this.queryForm.blogTitle = undefined;
        this.getBlogList();
      }
    },
    onSearch() {
      this.getBlogList();
    },
    cancel() {},
    confirm(item) {
      delBlog(item.blogId, this.tag.id).then((res) => {
        if (res.code == 200) {
          this.$message.success("操作成功");
          this.getBlogList()
        } else {
          this.$message.error("操作失败");
        }
      });
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
