// 选择题模板
<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>题目描述</span>
      </div>
      <mceeditor-dir v-model="titleDescription">
      </mceeditor-dir>
      Dir: {{titleDescription}}
    </el-card>
    <mceeditor-options
    v-for="(item, index) in options" 
    v-model="options[index]"
    :key="index"
    :currentIndex="index"
    :radio="radio"
    @plusOne="plusOneParent" 
    @deleteOne="deleteOneParent"
    @selectRadio="dealAnswer"
    @selectRadioByMultiple="dealAnswerByMultiple">
    </mceeditor-options>
    <div v-for="(item, index) in options" :key="index">
      Value:{{options[index]}}
    </div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>正确答案解析</span>
      </div>
      <mceeditor-dir v-model="correctAnswerAnalysis">
      </mceeditor-dir>
      Dir: {{correctAnswerAnalysis}}
    </el-card>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>错误答案解析</span>
      </div>
      <mceeditor-dir v-model="wrongAnswerAnalysis">
      </mceeditor-dir>
      Dir: {{wrongAnswerAnalysis}}
    </el-card>
  </div>
</template>

<script>
import mceeditorOptions from '@/views/modules/questionManagement/components/mceeditor-options'
import mceeditorDir from '@/views/modules/questionManagement/components/mceeditor-dir'
window.tinymce.baseURL = '/static/tinymce'
window.tinymce.suffix = '.min'
export default {
  data () {
    return {
      Value: '',
      radio: {type: 'singleSelection', val: 0, info: []},
      options: ['', '', '', ''],
      titleDescription: null,
      correctAnswerAnalysis: null,
      wrongAnswerAnalysis: null,
      topicInfo: null
    }
  },
  components: {
    mceeditorOptions,
    mceeditorDir
  },
  methods: {
    plusOneParent (value) {
      this.options.push('')
    },
    deleteOneParent (value) {
      this.$nextTick(() => {
        this.options.splice(value, 1)
        window.tinyMCE.editors.forEach((element, index) => {
          element.setContent(this.options[index])
        })
      })
    },
    dealAnswer (selectValue) {
      this.radio = {type: 'singleSelection', val: selectValue, info: []}
      console.log(this.radio)
    },
    dealAnswerByMultiple (selectValue) {
      let mark = {serialNumber: -1, count: 0}
      if (this.radio.info.length === 0) {
        this.radio.info.push(selectValue)
      }
      this.radio.info.forEach((item, index) => {
        if (item.serialNumber === selectValue.serialNumber) {
          mark.count++
          mark.serialNumber = selectValue.serialNumber
        }
      })
      if (mark.count === 1) {
        this.radio.info[mark.serialNumber].isCheck = selectValue.isCheck
      } else {
        this.radio.info.push(selectValue)
      }
      console.log(this.radio.info)
    },
    getData () {
      return Object.assign(
        {options: this.options},
        {titleDescription: this.titleDescription},
        {correctAnswerAnalysis: this.correctAnswerAnalysis},
        {wrongAnswerAnalysis: this.wrongAnswerAnalysis},
        {correctAnswer: this.radio}
      )
    }
  }
}
</script>
