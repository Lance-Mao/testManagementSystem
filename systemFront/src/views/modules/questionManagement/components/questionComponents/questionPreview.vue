// 试题预览
<template>
  <div>
    <div>
      <ul>
        <el-button type="primary" plain @click="demo">测试</el-button>
        <li class="titleDescription">{{'1、'}}{{questionInfo.titleDescription === null ? '暂无题目描述' : questionInfo.titleDescription}}</li>
        <li class="optionDescription" v-for="(item, index) in questionInfo.options" :key="index">{{String.fromCharCode(65 + index)}}、{{item === '' ? '无选项描述' : item}}</li>
      </ul>
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      current: 0
    }
  },
  props: {
    questionInfo: {}
  },
  mounted () {
    console.log(123)
    console.log(this.questionInfo)
    console.log(123)
  },
  methods: {
    demo () {
      debugger
    }
  }
}
</script>

<style>
.optionDescription {
  list-style: none;
  margin: 8px 8px;
}
.titleDescription {
  font-weight: bold;
  list-style: none;
  font-size: 24px;
}
</style>
