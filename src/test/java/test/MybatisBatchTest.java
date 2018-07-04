package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.myron.common.util.UuidUtils;
import com.myron.ims.bean.Goods;
import com.myron.ims.bean.User;
import com.myron.ims.dao.GoodsDao;
import com.myron.ims.dao.UserDao;
import com.myron.ims.service.GoodsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/config/spring/spring-context.xml")
public class MybatisBatchTest {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private GoodsDao goodsDao;
	
//	@Autowired
//	private GoodsService goodsServcie;
	
	@Test
	public void test(){
		List<User> list=new ArrayList<>();
		User user1= new User();
		user1.setId(UuidUtils.creatUUID());
		user1.setUsername("Name1");
		user1.setName("Tom");
		user1.setCreateDate(new Date());
		
		User user2= new User();
		user2.setId(UuidUtils.creatUUID());
		user2.setUsername("Name2");
		user2.setName("Jack");
		user2.setCreateDate(new Date());
		
		list.add(user1);
		list.add(user2);
		int flag=this.userDao.insertByBatch(list);
		System.out.println("MybatisBatchTest.test()"+flag);
	}
	
	//@Transactional
	//@Test
	public void testGoodsInsertByBatch(){
		List<Goods> list = new ArrayList<>();
		Goods goods = new Goods();
		goods.setGoodsId(UuidUtils.creatUUID());
		goods.setName("商品2-----");
		goods.setSaleStartDate(new Date());
		goods.setSaleEndDate(new Date());
		goods.setCreateTime(new Date());
		goods.setStatus("d");
		System.out.println(goods.toString());
		
		Goods goods1 = new Goods();
		goods1.setGoodsId(UuidUtils.creatUUID());
		goods1.setName("商品3----");
		goods1.setSaleStartDate(new Date());
		goods1.setSaleEndDate(new Date());
		goods1.setCreateTime(new Date());
		goods1.setStatus("d");
		System.out.println(goods1.toString());
		
		list.add(goods);
		list.add(goods1);
		
		int flag = this.goodsDao.insertByBatch(list);
		System.out.println("MybatisBatchTest.testGoodsInsertByBatch()"+flag);
	}
	
	//@Transactional
	//@Test
/*	public void testXAtransaction() throws Exception{
		List<Goods> list = new ArrayList<>();
		Goods goods = new Goods();
		goods.setGoodsId(UuidUtils.creatUUID());
		goods.setName("商品2-----");
		goods.setSaleStartDate(new Date());
		goods.setSaleEndDate(new Date());
		goods.setCreateTime(new Date());
		goods.setStatus("d");
		System.out.println(goods.toString());
		
		Goods goods1 = new Goods();
		goods1.setGoodsId(UuidUtils.creatUUID());
		goods1.setName("商品3----");
		goods1.setSaleStartDate(new Date());
		goods1.setSaleEndDate(new Date());
		goods1.setCreateTime(new Date());
		goods1.setStatus("d");
		System.out.println(goods1.toString());
		this.goodsServcie.createGoods(goods);
		this.goodsServcie.createGoods(goods1);
		list.add(goods);
		list.add(goods1);
		

	}*/

}
