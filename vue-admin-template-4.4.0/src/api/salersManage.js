import request from '@/utils/request'

export default {
  getSalersList(searchModel) {
    return request({
      url: '/auditors/lists',
      method: 'get',
      params: {
        pageNo: searchModel.pageNo,
        pageSize: searchModel.pageSize,
        salerName: searchModel.salerName,
        phone: searchModel.phone,
        remark: searchModel.remark,
        result: searchModel.result
      }
    })
  },
  addSalers(tosalers) {
    return request({
      url: '/auditors',
      method: 'post',
      data: tosalers
    })
  },
  saveSaler(tosalers) {
    if (tosalers.salerId == null) {
      return this.addSalers(tosalers)
    }
    return this.update(tosalers)
  },
  getSalerById(salerId) {
    return request({
      url: `/auditors/sid/${salerId}`,
      method: 'get'
    })
  },
  update(tosalers) {
    return request({
      url: '/auditors',
      method: 'put',
      date: tosalers
    })
  },
  updateResult(result, salerId) {
    return request({
      url: `/auditors/upresult?result=${result}&salerId=${salerId}`,
      method: 'post'
    })
  }
}
