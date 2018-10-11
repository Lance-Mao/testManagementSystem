<template>
	<div class="app">
    <el-row>
      <el-col :span="16">
        <div class="left" ref="left">
          <multiple-choice-question ref="examine"></multiple-choice-question>
        </div>
        <div class="bottom">
          <el-row>
            <el-col :offset="12">
              <el-button type="primary" @click="getChildData">提交</el-button>
              <el-button type="primary" @click="clickDemo">提交</el-button>
            </el-col>
          </el-row>
        </div>
      </el-col>
      <el-col :span="8">
        <question-preview
          :questionInfo="questionInfo">
        </question-preview>
        <el-button
          v-if="!showAllQuestions"
          type="success" 
          icon="el-icon-plus" 
          style="width: 50%; margin-left: 25%"
          @click="addNewQuestion">
          添加新题目
        </el-button>
        <ul v-else>
          <li v-for="(item, index) in questionTypes" style="listStyle: none; float: left; margin: 10px 10px" :key="index">
            <el-button
             round
             @click="chooseType(item)"
             >
              {{item.val}}
            </el-button>
          </li>
        </ul>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import multipleChoiceQuestion from '@/views/modules/questionManagement/components/questionComponents/multipleChoiceQuestion'
import questionPreview from '@/views/modules/questionManagement/components/questionComponents/questionPreview'
export default {
  data () {
    return {
      clientHeight: '',
      currentSubscript: 0,
      questionTypes: [{code: 'DANXT', val: '单选题'}, {code: 'DUOXT', val: '多选题'}, {code: 'PANDT', val: '判断题'}, {code: 'TIANKT', val: '填空题'}, {code: 'JIANDT', val: '简答题'}, {code: 'ZONGHT', val: '综合题'}],
      currentQuestionTypes: {code: '', val: ''},
      showAllQuestions: false,
      questionInfos: [],
      questionInfo: {}
    }
  },
  components: {
    multipleChoiceQuestion,
    questionPreview
  },
  mounted () {
    // 获取浏览器的可视高度
    this.clientHeight = `${document.documentElement.clientHeight}`
    window.onresize = function temp () {
      this.clientHeight = `${document.documentElement.clientHeight}`
    }
    this.dynamicGetQuestion()
  },
  watch: {
    // 如果 `clientHeight` 发生改变，这个函数就会运行
    clientHeight: function () {
      this.changeFixed(this.clientHeight)
    }
  },
  methods: {
    changeFixed (clientHeight) { // 动态修改样式
      this.$refs.left.style.height = clientHeight - 170 + 'px'
    },
    dynamicGetQuestion () {
      this.questionInfos.push(this.getChildData())
      this.questionInfo = this.questionInfos[this.currentSubscript]
    },
    clickDemo () {
      debugger
      console.log(this.questionInfo)
      console.log(123)
    },
    getChildData () {
      return this.$refs['examine'].getData()
    },
    addNewQuestion () {
      this.showAllQuestions = !this.showAllQuestions
    },
    chooseType (item) {
      let data
      if (item.code === 'DANXT') {
        data = this.$refs['examine'].getData()
      }
      this.questionInfos.push(data)
      this.currentQuestionTypes = item
      this.showAllQuestions = !this.showAllQuestions
    }
  }
}
</script>

<style>
	.app {
		width: 100%; 
	}
  .left {
    overflow: scroll;
    padding: 1px 2px;
  }
  .bottom {
    margin-top: 10px;
  }
</style>