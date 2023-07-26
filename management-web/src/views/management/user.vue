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
          <el-button type="primary" icon="el-icon-plus" circle></el-button>
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
  </div>
</template>


<script>
import userAPI from '@/api/userManagement'
export default{
  data() {
    return {
      total: 0,
      searchModel: {
        pageNo: 1,
        pageSize: 10
      },
      userList: []
    }
  },
  methods: {
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
</style>

