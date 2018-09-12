<template>
	<div class="app">
		  <!-- <mceeditor 
    :config           = "Config"
     v-model          = "Value"
    :url              = "Url"  
    :max-size         = "MaxSize"
    :accept           = "Accept"
    :with-credentials = false
    @on-ready         = "onEditorReady"
    @on-destroy       = "onEditorDestroy"
    @on-upload-success= "onEditorUploadComplete"
    @on-upload-fail   = "onEditorUploadFail"
  ></mceeditor> -->
		
<mceeditor v-model = "Value"></mceeditor>
 Value:{{Value}}
  </div>
</template>

<script>
	import mceeditor from '@/views/modules/questionManagement/components/mceeditor'
	window.tinymce.baseURL = '/static/tinymce'
	window.tinymce.suffix = '.min'
	export default{
	  components: {
	    mceeditor
  },
	  data () {
    return {
	      Value: ''
    }
  }
	}
</script>

<style>
	.app {
		width: 1204px; margin: 0 auto;
	}
</style>