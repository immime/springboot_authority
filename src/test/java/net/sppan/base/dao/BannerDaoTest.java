package net.sppan.base.dao;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wyjk.admin.dao.IBannerDao;
import com.wyjk.admin.entity.Banner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BannerDaoTest {
	
	@Autowired
	private IBannerDao dao;

	@Test
	public void test() {
		List<Banner> all = dao.findAll();
		System.out.println(all.size());
	}

}
