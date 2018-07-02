<template>
  <div class="mod-config">
    <panel-group @handleSetLineChartData="handleSetLineChartData" />
    <el-row :gutter="20">
      <el-col :span="24">
        <el-card v-show="currentCardType === 'testPaperList'">
          <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
            <el-form-item>
              <el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
            </el-form-item>
            <el-form-item>
              <el-button @click="getDataList()">查询</el-button>
              <el-button v-if="isAuth('questionManagement:questiontestpapergeneration:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
              <el-button v-if="isAuth('questionManagement:questiontestpapergeneration:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
            </el-form-item>
          </el-form>
          <el-table
            :data="dataList"
            border
            v-loading="dataListLoading"
            @selection-change="selectionChangeHandle"
            style="width: 100%;">
            <el-table-column
              type="selection"
              header-align="center"
              align="center"
              width="50">
            </el-table-column>
            <el-table-column
              prop="courseTitle"
              header-align="center"
              align="center"
              label="课程名称">
            </el-table-column>
            <el-table-column
              prop="testName"
              header-align="center"
              align="center"
              label="试卷名称">
            </el-table-column>
            <el-table-column
              prop="examiner"
              header-align="center"
              align="center"
              label="出题人">
            </el-table-column>
            <el-table-column
              prop="timeOfQuestion"
              header-align="center"
              align="center"
              label="出题时间">
            </el-table-column>
            <el-table-column
              prop="testContent"
              header-align="center"
              align="center"
              label="试卷内容">
            </el-table-column>
            <el-table-column
              fixed="right"
              header-align="center"
              align="center"
              width="150"
              label="操作">
              <template slot-scope="scope">
                <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.courseTitle)">修改</el-button>
                <el-button type="text" size="small" @click="deleteHandle(scope.row.courseTitle)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination
            @size-change="sizeChangeHandle"
            @current-change="currentChangeHandle"
            :current-page="pageIndex"
            :page-sizes="[10, 20, 50, 100]"
            :page-size="pageSize"
            :total="totalPage"
            layout="total, sizes, prev, pager, next, jumper">
          </el-pagination>
          <!-- 弹窗, 新增 / 修改 -->
          <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
        </el-card>
        <el-card v-show="currentCardType === 'questionBank'">
          <test-paper-question-bank ref="TestPaperQuestionBank" />
        </el-card>
        <el-card v-show="currentCardType === 'testPaperAnalysis'">
          试卷分析
        </el-card>
        <el-card v-show="currentCardType === 'popularPapers'">
          热门试卷
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import AddOrUpdate from './questiontestpapergeneration-add-or-update'
  import BoxCard from '../questionManagement/components/BoxCard.vue'
  import PanelGroup from '../questionManagement/components/PanelGroup.vue'
  import TestPaperQuestionBank from './questiontestpapergeneration/testpaper-questionBank.vue'
  export default {
    data () {
      return {
        dataForm: {
          key: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false,
        currentCardType: 'questionBank'
      }
    },
    components: {
      BoxCard,
      AddOrUpdate,
      PanelGroup,
      TestPaperQuestionBank
    },
    activated () {
      this.getDataList()
      this.$refs.TestPaperQuestionBank.init()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/questionManagement/questiontestpapergeneration/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },
      handleSetLineChartData (type) {
        console.log(type, '当前类型')
        this.currentCardType = type
        if (type === 'questionBank') {
          this.$refs.TestPaperQuestionBank.init()
        }
      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.courseTitle
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/questionManagement/questiontestpapergeneration/delete'),
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      }
    }
  }
</script>
