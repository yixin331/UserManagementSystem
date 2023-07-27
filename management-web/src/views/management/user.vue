<template>
  <div>
    <el-card class="box-card" id="search">
      <el-row>
        <el-col :span="20">
          <el-input placeholder="Username" v-model="searchModel.username" clearable> </el-input>
                <el-input placeholder="Email" v-model="searchModel.email" clearable> </el-input>
                <el-button @click="getUserList" type="primary" icon="el-icon-search">Search</el-button>
        </el-col>
        <el-col :span="4" align="right">
          <el-button @click="openEditUI" type="primary" icon="el-icon-plus" circle></el-button>
        </el-col>
      </el-row>
    </el-card>

    <el-card class="box-card">
      <el-table :data="userList" stripe style="width: 100%">
          <el-table-column label="#" width="80">
            <template slot-scope="scope">
              {{(searchModel.pageNo - 1) * searchModel.pageSize + scope.$index + 1}}
            </template>
          </el-table-column>
          <el-table-column prop="userid" label="User ID" width="80">
          </el-table-column>
          <el-table-column prop="username" label="Username" width="120">
          </el-table-column>
          <el-table-column prop="lastname" label="Last Name" width="120">
          </el-table-column>
          <el-table-column prop="firstname" label="First Name" width="120">
          </el-table-column>
          <el-table-column prop="email" label="Email">
          </el-table-column>
          <el-table-column prop="phone" label="Phone" width="120">
          </el-table-column>
          </el-table-column>
          <el-table-column prop="status" label="Status" width="80">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.status == 1">Active</el-tag>
              <el-tag v-else type="danger">Inactive</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="Operations" width="120">
          </el-table-column>
        </el-table>
    </el-card>

    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page.sync="searchModel.pageNo"
      :page-sizes="[5, 10, 20, 30]"
      :page-size="searchModel.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>

    <el-dialog @close="clearForm" :title="title" :visible.sync="dialogFormVisible">
      <el-form :model="userForm" ref="userFormRef" :rules="rules">
        <el-form-item label="Username" prop="username" :label-width="formLabelWidth">
          <el-input v-model="userForm.username" autocomplete="off" clearable></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="password" :label-width="formLabelWidth">
          <el-input type="password" v-model="userForm.password" autocomplete="off" clearable></el-input>
        </el-form-item>
        <el-form-item label="Last Name" :label-width="formLabelWidth">
          <el-input v-model="userForm.lastname" autocomplete="off" clearable></el-input>
        </el-form-item>
        <el-form-item label="First Name" :label-width="formLabelWidth">
          <el-input v-model="userForm.firstname" autocomplete="off" clearable></el-input>
        </el-form-item>
        <el-form-item label="Email" prop="email" :label-width="formLabelWidth">
          <el-input v-model="userForm.email" autocomplete="off" clearable></el-input>
        </el-form-item>
        <el-form-item label="Phone" prop="phone" :label-width="formLabelWidth">
          <el-input v-model="userForm.phone" autocomplete="off" clearable></el-input>
        </el-form-item>
        <el-form-item label="Status" :label-width="formLabelWidth">
          <el-switch v-model="userForm.status" :active-value="1" :inactive-value="0">
          </el-switch>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button type="primary" @click="saveUser">Confirm</el-button>
      </span>
    </el-dialog>
  </div>
</template>


<script>
import userAPI from '@/api/userManagement'
export default{
  data() {
    var checkUsername = (rule, value, callback) => {
      var reg = /^[a-z][a-z0-9]*$/;
      if (!reg.test(value)) {
        return callback(new Error("Lowercase letter start, letters and numbers only"));
      }
      callback();
    };

    var checkPhone = (rule, value, callback) => {
      var reg = /^(\(\+[0-9]{2}\))?([0-9]{3}-?)?([0-9]{3})\-?([0-9]{4})(\/[0-9]{4})?$/;
      if (!reg.test(value)) {
        if (value){
          return callback(new Error("Wrong phone format"));
        }
      }
      callback();
    };

    return {
      formLabelWidth: '130px',
      userForm: {},
      dialogFormVisible: false,
      title: "",
      total: 0,
      searchModel: {
        pageNo: 1,
        pageSize: 10
      },
      userList: [],
      rules: {
        username: [
          { required: true, message: 'Please input Username', trigger: 'blur' },
          { min: 5, max: 50, message: 'Length should be 5 to 50', trigger: 'blur' },
          { validator: checkUsername, trigger: ['blur', 'change'] },
        ],
        password: [
          { required: true, message: 'Please input Password', trigger: 'blur' },
          { min: 6, max: 30, message: 'Length should be 6 to 30', trigger: 'blur' }
        ],
        phone: [
          { validator: checkPhone, trigger: ['blur', 'change'] }
        ],
        email: [
          { required: true, message: 'Please input Email', trigger: 'blur' },
          { type: 'email', message: 'Please input correct Email', trigger: ['blur', 'change'] }
        ]
      }
    }
  },
  methods: {
    saveUser() {
      this.$refs.userFormRef.validate((valid) => {
        if (valid) {
          // submit to backend
          userAPI.addUser(this.userForm).then(response => {

            // success
            this.$message({
              message: response.message,
              type: 'success'
            });

            // close form
            this.dialogFormVisible = false;

            // refresh
            this.getUserList();

          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    clearForm() {
      this.userForm = {};
      this.$refs.userFormRef.clearValidate();
    },
    openEditUI() {
      this.title = 'New User';
      this.dialogFormVisible = true;
    },
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize;
      this.getUserList();
    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo;
      this.getUserList();
    },
    getUserList() {
      userAPI.getUserList(this.searchModel).then(response => {
        this.userList = response.data.rows;
        this.total = response.data.total;
      });
    }
  },
  created() {
    this.getUserList();
  }
}
</script>


<style>
#search .el-input {
  width: 200px;
  margin-right: 10px;
}
.el-dialog .el-input {
  width: 85%;
}
</style>

