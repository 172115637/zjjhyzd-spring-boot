import Mock from 'mockjs'
import { param2Obj } from '@/utils'

const List = []
const count = 10
for (let i = 0; i < count; i++) {
  List.push(Mock.mock({
    tagId: i+1,
    tagName: '标签分类名称'+(i+1),
    createAt: '2018.08.12 11:20:08',
  }))
}
const categoryList = []
for (let i = 0; i < count; i++) {
  categoryList.push(Mock.mock({
    categoryId: i+1,
    categoryName: '资讯分类名称'+(i+1),
    createAt: '2018.08.12 11:20:08',
  }))
}

// 资讯列表页专用CategoryList
const categoryOptions = []
for (let i = 0; i < 5; i++) {
  categoryOptions.push(Mock.mock({
    categoryId: i+1,
    categoryName: '分类'+(i+1),
  }))
}
// 资讯列表页专用TagList
const tagOptions = []
for (let i = 0; i < 5; i++) {
  tagOptions.push(Mock.mock({
    tagId: i+1,
    tagName: '标签'+(i+1),
  }))
}
// 资讯列表专用NewsList
const newsList = []
const Random = Mock.Random;
for (let i = 0; i < count; i++) {
  newsList.push(Mock.mock({
    newsId: i+1,
    newsTitle: '标题内容标题内容标题内容'+(i+1),
    newsCategory: '分类名称'+(i%5+1),
    newsCategoryId: i+1,
    newsTag: '标签名称'+(i%5+1),
    newsTagId: i+1,
    newsImgurl: 'src/assets/images/news.jpg',//Random.dataImage('300x250', 'mock的图片'),
    newsContent: (i+1)+'Every month we will deliver the very best articles, together with the latest fashion and beauty news, together with the latest fashion and beauty news',
    zanCnt: 10+i,
    favCnt: 20+i,
    readCnt: 30+i
  }))
}


export default {
	
	// 标签分类相关
  fetchTagList: config => {
  	console.log('--mock fetchTagList---')
    return {
			code:20000,
			data:{
	    	items: List,
	    	total: parseInt(10 + (90 - 10) * (Math.random()))
			},
			message:''
    }
  },

  executeCreateTag: config => {
  	console.log('---mock executeCreateTag --')
		return {
			code:20000,
			data:{},
			message:'创建成功'
		}
  },

  executeUpdateTag: config => {
  	console.log('---mock executeUpdateTag --')
		return {
			code:20000,
			data:{},
			message:'更新成功'
		}
  },

  executeDelTag: config => {
  	console.log('---mock executeDelTag --')
		return {
			code:20000,
			data:{},
			message:'更新成功'
		}
  },

	// 资讯分类相关
  fetchCategoryList: config => {
  	console.log('--mock fetchCategoryList---')
    return {
			code:20000,
			data:{
	    	items: categoryList,
	    	total: parseInt(10 + (90 - 10) * (Math.random()))
			},
			message:''
    }
  },

  executeCreateCategory: config => {
  	console.log('---mock executeCreateCategory --')
		return {
			code:20000,
			data:{},
			message:'创建成功'
		}
  },

  executeUpdateCategory: config => {
  	console.log('---mock executeUpdateCategory --')
		return {
			code:20000,
			data:{},
			message:'更新成功'
		}
  },

  executeDelCategory: config => {
  	console.log('---mock executeDelCategory --')
		return {
			code:20000,
			data:{},
			message:'删除成功'
		}
  },

	// 资讯列表页面相关
  fetchCategoryOptions: config => {
  	console.log('--mock fetchCategoryOptions ---')
    return {
			code:20000,
			data:{
	    	items: categoryOptions
			},
			message:''
    }
  },
  
  fetchTagOptions: config => {
  	console.log('--mock fetchTagOptions---')
    return {
			code:20000,
			data:{
	    	items: tagOptions
			},
			message:''
    }
  },

  fetchNewsList: config => {
  	console.log('--mock fetchNewsList---')
    return {
			code:20000,
			data:{
	    	items: newsList,
	    	total: parseInt(10 + (90 - 10) * (Math.random()))
			},
			message:''
    }
  },

  executeCreateNews: config => {
  	console.log('---mock executeCreateNews --')
		return {
			code:20000,
			data:{},
			message:'创建成功'
		}
  },

  executeUpdateNews: config => {
  	console.log('---mock executeUpdateNews --')
		return {
			code:20000,
			data:{},
			message:'更新成功'
		}
  },

  executeDelNews: config => {
  	console.log('---mock executeDelNews --')
		return {
			code:20000,
			data:{},
			message:'删除成功'
		}
  },

}
