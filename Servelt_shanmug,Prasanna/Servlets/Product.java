package prasanna;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class Product {

	String ProductId;
	String ProductName;
	String ProductType;
	String ProductPrice;
	String ProductSpec;


	public String getProductName() {
		return ProductName;
	}

	
	public void setProductName(String ProductName) {
		this.ProductName = ProductName;
	}

	public String getProductId() {
		return ProductId;
	}


	public void setProductId(String ProductId) {
		this.ProductId = ProductId;
	}

	public String getProductPrice() {
		return ProductPrice;
	}

	
	public void setProductPrice(String ProductPrice) {
		this.ProductPrice = ProductPrice;
	}

    public String getProductType() {
		return ProductType;
	}

	
	public void setProductType(String ProductType) {
		this.ProductType = ProductType;
	}

	public String getProductSpec() {
		return ProductSpec;
	}

	
	public void setProductSpec(String ProductSpec) {
		this.ProductSpec = ProductSpec;
	}
}