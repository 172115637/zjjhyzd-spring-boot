import Mock from 'mockjs'
import { param2Obj } from '@/utils'

const List = []
const count = 10

// 检测项目List
for (let i = 0; i < count; i++) {
  List.push(Mock.mock({
  	itemId:'100'+i,
  	itemName:'检测项目'+(i+1),
  	itemImgUrl:'https://img03.sogoucdn.com/net/a/04/link?url=http%3A%2F%2Fimg04.sogoucdn.com%2Fapp%2Fa%2F100520024%2F8a5cac4b4c8819b2b7b3014f952409be&appid=122',
  	itemDescription:i+'描述xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx',
  	'status|1': ['published', 'deleted'],
  	remarks:''
  }))
}

export default {
	// 检测项目列表
	fetchItemList: config => {
  	console.log('--mock fetchItemList---')
    return {
			code:20000,
			data:{
	    	items: List,
	    	total: parseInt(10 + (90 - 10) * (Math.random()))
			},
			message:''
    }
 },
  
  
  
  executeCreateItem: config => {
  	console.log('---mock executeCreateItem --')
		return {
			code:20000,
			data:{},
			message:'创建成功'
		}
  },

}
