import Mock from 'mockjs'
import { param2Obj } from '@/utils'

const List = []
const count = 10

/**
 * 专家列表
 */
// 专家列表页专用DoctorTitleOptions
const doctorTitleOptions = []
for (let i = 0; i < 5; i++) {
  doctorTitleOptions.push(Mock.mock({
    doctorTitleId: i+1,
    doctorTitleName: '医生职称'+(i+1),
  }))
}
// 专家列表页专用SubjectList
const subjectOptions = []
for (let i = 0; i < 5; i++) {
  subjectOptions.push(Mock.mock({
    subjectId: i+1,
    subjectName: '科目'+(i+1),
  }))
}
// 专家列表页专用DepartmentList
const departmentOptions = []
for (let i = 0; i < 5; i++) {
  departmentOptions.push(Mock.mock({
    departmentId: i+1,
    departmentName: '科室'+(i+1),
  }))
}
// 专家列表专用DoctorList
const doctorList = []
const Random = Mock.Random;
for (let i = 0; i < count; i++) {
  doctorList.push(Mock.mock({
    doctorId: '@increment',
    doctorName: '@first',
    doctorPhone: '@first',
    'subjectId|1': [1, 2, 3, 4,5],
    subjectName:'科目'+i,
    'departmentId|1': [1, 2, 3, 4,5],
    departmentName:'科室'+i,
    curesDiseases: '呼吸内科、消化内科',
    patientNum: '@integer(1, 30)',
    praiseRate: '85.12%',
    serviceNum: '@integer(1, 20)',
    'doctorTitleId|1': [1, 2, 3, 4,5],
    doctorTitleName:'医生职称'+i,
    'status|1': ['published', 'deleted']
  }))
}


export default {
	
	//专家列表Data
  fetchDoctorList: config => {
  	console.log('--mock fetchDoctorList---')
    return {
			code:20000,
			data:{
	    	items: doctorList,
	    	total: parseInt(10 + (90 - 10) * (Math.random()))
			},
			message:''
    }
  },
  //专家列表页医生职称Data
  fetchDoctorTitleOptions: config => {
  	console.log('--mock fetchDoctorTitleOptions ---')
    return {
			code:20000,
			data:{
	    	items: doctorTitleOptions
			},
			message:''
    }
  },
  //专家列表页科目Data
  fetchSubjectOptions: config => {
  	console.log('--mock fetchSubjectOptions ---')
    return {
			code:20000,
			data:{
	    	items: subjectOptions
			},
			message:''
    }
  },
  //专家列表页科室Data
  fetchDepartmentOptions: config => {
  	console.log('--mock fetchDepartmentOptions ---')
    return {
			code:20000,
			data:{
	    	items: departmentOptions
			},
			message:''
    }
  },
  //更新专家信息
  executeUpdateDoctor: config => {
  	console.log('---mock executeUpdateDoctor --')
		return {
			code:20000,
			data:{},
			message:'更新成功'
		}
  },
  //创建专家信息
  executeCreateDoctor: config => {
  	console.log('---mock executeCreateDoctor --')
		return {
			code:20000,
			data:{},
			message:'新增成功'
		}
  },
}
