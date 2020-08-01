package com.bookstore.admin.products.handler;

import com.bookstore.admin.products.service.IAdminProductService;
import com.bookstore.commons.beans.Product;
import com.bookstore.commons.beans.ProductList;
import com.bookstore.utils.UUidUtil;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("/admin/product")
public class AdminProductHandler {
    @Autowired
    private IAdminProductService iAdminProductService;

    //显示商品列表
    @RequestMapping("/listProduct")
    public String listProduct(Model model){
        List<Product> products = iAdminProductService.findAllProduct();
        model.addAttribute("products",products);
        return "/admin/products/list.jsp";
    }

    //复合查询商品信息
    @RequestMapping("/findProductByManyCondition")
    public String findProductByManyCondition(Product product,Double minprice, Double maxprice , Model model){
        List<Product> products = iAdminProductService.findProductByManyCondition(product,minprice,maxprice);
        model.addAttribute("product",product);
        model.addAttribute("products",products);
        model.addAttribute("minprice",minprice);
        model.addAttribute("maxprice",maxprice);
        return "/admin/products/list.jsp";
    }

    //添加商品
    @RequestMapping("/addProduct")
    public String addProduct(Product product, MultipartFile upload, HttpSession session) throws IOException {
        //获取项目发布路径
        String path = session.getServletContext().getRealPath("/productImg");
        //获取本地项目路径
        String localPath = "E:\\Idea Projects\\bookstore\\src\\main\\webapp\\productImg";

        //文件上传路径不存在则创建路径
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        File localFile = new File(localPath);
        if(!localFile.exists()){
            localFile.mkdirs();
        }

        //通过UUID加工文件名，防止文件名重复
        String filename = UUidUtil.getUUID() + "-" +upload.getOriginalFilename();
        upload.transferTo(new File(path,filename));
        FileUtils.copyFile(new File(path,filename),new File(localPath,filename));

        //添加商品信息到数据库
        product.setId(UUidUtil.getUUID());
        product.setImgURL("/productImg/"+filename);
        iAdminProductService.addProduct(product);
        return "redirect:/admin/product/listProduct";
    }

    //按id查询商品信息
    @RequestMapping("/findProductById")
    public String findProductById(String id,Model model){
        Product product = iAdminProductService.findProductById(id);
        model.addAttribute("p",product);
        return "/admin/products/edit.jsp";
    }

    //编辑商品信息
    @RequestMapping("/editProduct")
    public String editProduct(Product product, MultipartFile upload, HttpSession session) throws IOException {
        //是否需要更改商品图片
        if(!upload.isEmpty()){
            //1.删除原始图片
            //获取原始商品信息
            Product target = iAdminProductService.findProductById(product.getId());
            //1.删除项目路径文件,项目发布根路径+imgURL
            File targetFile1 = new File(session.getServletContext().getRealPath("/")+target.getImgURL());
            //如果商品图片存在，则删除
            if(targetFile1.exists()){
                targetFile1.delete();
            }
            //2.删除本地文件
            String oldPath = "E:\\Idea Projects\\bookstore\\src\\main\\webapp\\productImg";
            String oldFilename = target.getImgURL().substring(11);
            File targetFile2 = new File(oldPath,oldFilename);
            //如果商品图片存在，则删除
            if(targetFile2.exists()){
                targetFile2.delete();
            }

            //上传新图片
            String path = session.getServletContext().getRealPath("/productImg");
            //获取本地项目路径
            String localPath = "E:\\Idea Projects\\bookstore\\src\\main\\webapp\\productImg";
            //设置图片名称
            String filename = UUidUtil.getUUID() + "-" + upload.getOriginalFilename();
            upload.transferTo(new File(path,filename));
            FileUtils.copyFile(new File(path,filename),new File(localPath,filename));
            product.setImgURL("/productImg/"+filename);
        }

        //更改商品数据库信息
        iAdminProductService.editProduct(product);
        //返回商品列表
        return "redirect:/admin/product/listProduct";
    }

    //删除商品
    @RequestMapping("/removeProduct")
    public String removeProduct(String id ,HttpSession session){
        //获取商品信息
        Product target = iAdminProductService.findProductById(id);
        //1.删除项目路径文件,项目发布根路径+imgURL
        File targetFile1 = new File(session.getServletContext().getRealPath("/")+target.getImgURL());
        //如果商品图片存在，则删除
        if(targetFile1.exists()){
            targetFile1.delete();
        }
        //2.删除本地文件
        String path = "E:\\Idea Projects\\bookstore\\src\\main\\webapp\\productImg";
        String filename = target.getImgURL().substring(11);
        File targetFile2 = new File(path,filename);
        //如果商品图片存在，则删除
        if(targetFile2.exists()){
            targetFile2.delete();
        }

        //删除数据库信息
        int row = iAdminProductService.removeProduct(id);
        return "redirect:/admin/product/listProduct";
    }

    //销售榜单下载
    @RequestMapping("/download")
    public void download(String year, String month, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //查询销售榜单
        List<ProductList> plist = iAdminProductService.findProductSalList(year,month);

        String filename = year + "年" + month + "月销售榜单";
        String sheetName = month + "月";
        String titleName = year + "年" + month + "月销售榜单";

        String[] columnName = {"商品名称","商品销量"};

        String[][] dataList = new String[plist.size()][2];
        for (int i = 0; i < plist.size(); i++) {
            dataList[i][0] = plist.get(i).getName();
            dataList[i][1] = plist.get(i).getSalNum();
        }

        //创建Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();

        //创建文件的Sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        //创建sheet第一行
        HSSFRow row = sheet.createRow(0);
        //创建sheet第一行的第一个单元格
        HSSFCell cell = row.createCell(0);
        //合并第一行的两个单元格
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,1));

        //为第一行的第一个单元格赋值
        cell.setCellValue(titleName);

        //创建sheet第二行
        HSSFRow rows = sheet.createRow(1);
        for (int i = 0; i < 2 ; i++) {
            rows.createCell(i).setCellValue(columnName[i]);
        }

        //创建商品数据行
        for (int i = 0; i < plist.size(); i++) {
            rows = sheet.createRow(2+i);
            for (int j = 0; j < 2; j++) {
                rows.createCell(j).setCellValue(dataList[i][j]);
            }
        }

        String fileName = filename + ".xls";
        response.setContentType("application/ms-excel;charset=UTF-8");
        response.setHeader("content-Disposition","attachment;filename="+processFileName(request,fileName));
        OutputStream out = response.getOutputStream();
        wb.write(out);
    }

    //解决IE，Chrome，Firefox文件下载乱码问题
    public String processFileName(HttpServletRequest request,String fileNames){
        String codeFileName = null;
        try{
            String agent = request.getHeader("USER-AGENT");
            if(null != agent && -1 != agent.indexOf("MSIE") || null != agent && -1 != agent.indexOf("Trident")){
                //IE
                String name = java.net.URLEncoder.encode(fileNames,"UTF8");
                codeFileName = name;
            } else if(null != agent && -1 != agent.indexOf("Mozilla")){
                //火狐，Chrome等
                codeFileName = new String(fileNames.getBytes("UTF-8"),"iso-8859-1");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return codeFileName;
    }
}
