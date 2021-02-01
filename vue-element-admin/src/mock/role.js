import Mock from 'mockjs'
import { param2Obj } from '@/utils'

const List = []
const role = {
	id: '008091',
    roleId: 'aaa001',
    roleName: 'update用户名',
    power: [{id: '0001', name: '查看订单数据'}],
    roleDes: 'update角色描述xxxxxxxxxxxx',
    roleStatus: 0,
    createAt: '2018.08.12 11:20:08',
}
const count = 10
for (let i = 0; i < count; i++) {
  List.push(Mock.mock({
    id: '@increment',
    roleId: '@increment',
    roleName: '用户名',
    power: [{id: '0001', name: '查看订单数据'},{id: '0002', name: '权限AAA'}],
    roleDes: '角色描述xxxxxxxxxxxx',
    roleStatus: 0,
    createAt: '2018.08.12 11:20:08',
  }))
}
const accountList = []
const account = {
	accountId: '0001',
	accountNameEn: 'zhangs',
	accountNameCh: '张三',
	accountNumber: '0010',
	accountPhone: '18900000000',
	roles: [{id: '001', name : '运营人员'}, {id: '002',name: '管理员'}],
	accountStatus: 0,
	password: 111111,
	createAt: '2018.08.12 11:20:09'
}
for (let i = 0; i < count; i++) {
  accountList.push(Mock.mock({
    accountId: '@increment',
   	accountNameEn: 'zhangs',
	accountNameCh: '张三',
	accountNumber: '0010',
	accountPhone: '18900000000',
	roles: [{id: '001', name: '运营人员'}, {id: '002', name: '管理员'}],
	accountStatus: 0,
	createAt: '2018.08.12 11:20:08',
  }))
}

export default {
  //获取分页角色列表	
  getList: config => {
  	console.log('--mock role getList---')
    return {
    	code: 20000,
    	data: {
    		items: List,
    		total: parseInt(10 + (90 - 10) * (Math.random()))
    	},
    	message: 'success'
    }
  },
  // 修改角色状态
  modifyRoleStatus: config => {
  	console.log('---mock role status --')
  	const { roleStatus } = param2Obj(config.url)
  	console.log('config.url')
  	console.log(roleStatus)
	return {
		code:20000,
		data:{},
		message:'修改成功'
	}
  },
  // 编辑角色
  updateRole: config => {
  	console.log('---mock updateRole --')
  	return {
  		code: 20000,
  		data: {},
  		message: '修改成功'
  	}
  },
  // 新建角色
  createRole: config => {
  	console.log('---mock createRole --')
  	return {
  		code: 20000,
  		data: {},
  		message: '创建成功'
  	}
  },
  //账户列表
   getAccountList: config => {
  	console.log('--mock accountList getList---')
    return {
    	code: 20000,
    	data: {
    		items: accountList,
    		total: parseInt(10 + (90 - 10) * (Math.random()))
    	},
    	message: 'success'
    }
  },
  //修改账号状态
   modifyAccountStatus: config => {
  	console.log('-- mock modifyAccountStatus ---')
    return {
		code:20000,
		data:{},
		message:'修改成功'
	}
  },
  // 编辑账户
  updateAccount: config => {
  	console.log('---mock updateAccount --')
  	return {
  		code: 20000,
  		data: {},
  		message: '修改成功'
  	}
  },
  // 重置密码
  resetAccount: config => {
  	console.log('---mock resetAccount --')
  	return {
  		code: 20000,
  		data: {},
  		message: '重置成功'
  	}
  },
  // 新建账户
  createAccount: config => {
  	console.log('---mock createAccount --')
  	return {
  		code: 20000,
  		data: {},
  		message: '创建成功'
  	}
  },
}
