import request from '@/utils/request'

const api_name = '/admin/system/sysRole'

export default {
    getPageList(page, limit, searchObj) {
        return request({
            url: `${api_name}/${page}/${limit}`,
            method: 'get',
            params: searchObj
        })
    },
    removeId(id) {
        return request({
            url: `${api_name}/remove/${id}`,
            method: 'delete'
        })
    },
    batchRemove(idList) {
        return request({
            url: `${api_name}/batchRemove`,
            method: 'delete',
            data: idList
        })
    },
    saveRole(role) {
        return request({
            url: `${api_name}/save`,
            method: 'post',
            data: role
        })
    },
    getRoleId(id) {
        return request({
            url: `${api_name}/findRoleById/${id}`,
            method: 'post'
        })
    },
    update(role) {
        return request({
            url: `${api_name}/update`,
            method: 'post',
            data: role
        })
    },
    //根据用户id查询用户已分配的角色
    getRolesByUserId(userId) {
        return request({
        url: `${api_name}/toAssign/${userId}`,
        method: 'get'
        })
    },
    
    //分配角色
    assignRoles(assginRoleVo) {
        return request({
        url: `${api_name}/doAssign`,
        method: 'post',
        data: assginRoleVo
        })
    }
}
