<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="80px">
      <!--    <el-form-item label="图片地址" prop="path">-->
      <!--      <el-input v-model="dataForm.path" placeholder="图片地址"></el-input>-->
      <!--    </el-form-item>-->
      <el-form-item label="备注" prop="remarks">
        <el-input v-model="dataForm.remarks" placeholder="备注"></el-input>
      </el-form-item>
      <el-form-item label="参数" prop="param">
        <el-input v-model="dataForm.param" placeholder="参数"></el-input>
      </el-form-item>
      <el-upload
        class="upload-demo"
        :action="this.$http.adornUrl(`/sys/oss/upload?token=${this.$cookie.get('token')}`)"
        name="file"
        multiple="false"
        :limit="1"
        ref="vvv"
        :on-success="handleUpload">
        <a :href="this.dataForm.path">{{ this.dataForm.path }}</a>
        <br>
        <el-button size="small" type="primary">点击上传</el-button>

      </el-upload>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  data () {
    return {
      visible: false,
      dataForm: {
        id: 0,
        path: '',
        remarks: '',
        param: '',
        status: '',
        sort: ''
      },
      dataRule: {
        path: [
          {required: true, message: '图片地址不能为空', trigger: 'blur'}
        ],
        remarks: [
          {required: true, message: '备注不能为空', trigger: 'blur'}
        ],
        status: [
          {required: true, message: '显示状态不能为空', trigger: 'blur'}
        ],
        sort: [
          {required: true, message: '排序字段不能为空', trigger: 'blur'}
        ],
        param: [
          {required: true, message: '参数不能为空', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    handleUpload (res) {
      this.dataForm.path = res.url
      this.$refs.vvv.clearFiles()
    },
    init (id) {
      this.dataForm.path = ''
      this.dataForm.id = id || 0
      this.visible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (this.dataForm.id) {
          this.$http({
            url: this.$http.adornUrl(`/substance/banner/info/${this.dataForm.id}`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.dataForm.path = data.banner.path
              this.dataForm.remarks = data.banner.remarks
              this.dataForm.status = data.banner.status
              this.dataForm.sort = data.banner.sort
              this.dataForm.param = data.banner.param
            }
          })
        }
      })
    },
    // 表单提交
    dataFormSubmit () {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(`/substance/banner/${!this.dataForm.id ? 'save' : 'update'}`),
            method: 'post',
            data: this.$http.adornData({
              'id': this.dataForm.id || undefined,
              'path': this.dataForm.path,
              'remarks': this.dataForm.remarks,
              'status': this.dataForm.status,
              'sort': this.dataForm.sort,
              'param': this.dataForm.param
            })
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.visible = false
                  this.$emit('refreshDataList')
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        }
      })
    }
  }
}
</script>
