package shop.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import shop.bean.Product;
import shop.bean.User;
import shop.dao.ProductDao;
import shop.dao.UserDao;

public class Test {
	public static void main(String[] args) throws IOException {
		ProductDao dao = new ProductDao();
		List<Product> lists = new ArrayList<>();
		lists = dao.AllProductsSearch();
		System.out.println(lists);
	}
}
