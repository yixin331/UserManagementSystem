import request from '@/utils/request'

export default {
  getUserList(searchModel) {
    return request ({
      url: '/user/list',
      method: 'get',
      params: {
        pageNo: searchModel.pageNo,
        pageSize: searchModel.pageSize,
        username: searchModel.username,
        email: searchModel.email
      }
    });
  },
  addUser(user) {
    return request ({
      url: '/user',
      method: 'post',
      data: user
    });
  }
}
