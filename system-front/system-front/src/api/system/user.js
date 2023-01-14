import request from '@/utils/request'

const api_name = '/admin/system/sysUser'

export default {
    getPageList(page, limit, searchObj) {
        return request({
            url: `${api_name}/${page}/${limit}`,
            method: 'get',
            params: searchObj
        })
    },
    removeById(id) {
        return request({
            url: `${api_name}/remove/${id}`,
            method: 'delete'
        })
    },
    save(user) {
        return request({
            url: `${api_name}/save`,
            method: 'post',
            data: user
        })
    },
    getUserId(id) {
        return request({
            url: `${api_name}/getUser/${id}`,
            method: 'get'
        })
    },
    update(user) {
        return request({
            url: `${api_name}/update`,
            method: 'post',
            data: user
        })
    },
    updateStatus(id,status) {
        return request({
            url: `${api_name}/updateStatus/${id}/${status}`,
            method: 'get',
        })
    }
}
