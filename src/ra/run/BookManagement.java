package ra.run;

import ra.model.Catalog;
import ra.model.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BookManagement {
    static List<Catalog> catalogList = new ArrayList<>();
    static List<Product> products = new ArrayList<>();


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("**************************BASIC-MENU**************************");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.println("Sự lựa chọn của bạn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    catalogManagement(sc);
                    break;
                case 2:
                    productManagement(sc);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-3:");
            }
        } while (true);
    }

    /////////////////catalogManagement////////////
    public static void catalogManagement(Scanner sc) {
        boolean flag = true;
        do {
            System.out.println("********************CATALOG-MANAGEMENT********************");
            System.out.println("1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục");
            System.out.println("2. Hiển thị thông tin tất cả các danh mục");
            System.out.println("3. Sửa tên danh mục theo mã danh mục");
            System.out.println("4. Xóa danh muc theo mã danh mục (lưu ý ko xóa khi có sản phẩm)");
            System.out.println("5. Quay lại");
            System.out.println("Sự lựa chọn của bạn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    create(sc);
                    break;
                case 2:
                    getAll();
                    break;
                case 3:
                    update(sc);
                    break;
                case 4:
                    delete(sc);
                    break;
                case 5:
                    flag = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-5");
            }
        } while (flag);
    }

    public static void create(Scanner sc) {
        System.out.println("Mời bạn nhập số lượng bạn muốn thêm danh muc: ");
        int num = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < num; i++) {
            System.out.println("Mời bạn nhập thông tin danh mục thứ: " + (i + 1));
            Catalog catalog1 = new Catalog();
            catalog1.setCatalogId(i + 1);
            catalog1.inputData();
            catalogList.add(catalog1);
        }
        System.out.println("Bạn đã thêm mới thành công " + num + " danh mục");
    }

    public static void getAll() {
        for (Catalog c : catalogList) {
            c.displayData();
        }
    }

    public static void update(Scanner sc) {
        System.out.println("Mời bạn nhập id");
        int id = Integer.parseInt(sc.nextLine());
        for (Catalog cat : catalogList) {
            if (cat.getCatalogId() == id) {
                System.out.println("Mời bạn nhập tên danh mục cần update:");
                String nam = sc.nextLine();
                cat.setCatalogName(nam);
                break;
            }
        }
    }

    public static void delete(Scanner sc) {
        System.out.println("Mời bạn nhập id cần xóa: ");
        int id = Integer.parseInt(sc.nextLine());
        for (Catalog cat : catalogList) {
            if (cat.getCatalogId() == id) {
                catalogList.remove(cat);
                break;
            }
        }
    }

    public static void productManagement(Scanner sc) {
        boolean flag = true;
        do {
            System.out.println("********************PRODUCT-MANAGEMENT********************");
            System.out.println("1. Nhập số sản sản phẩm và nhập thông tin sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Sắp xếp sản phẩm theo giá giảm dần");
            System.out.println("4. Xóa sản phẩm theo mã ");
            System.out.println("5. Tìm kiếm sách theo tên sách");
            System.out.println("6. Thay đổi thông tin của sách theo mã sách");
            System.out.println("7. Quay lại");
            System.out.println("Sự lựa chọn của ban: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    createProduct(sc);
                    break;
                case 2:
                    getAllProduct();
                    break;
                case 3:
                    sortExprotASC();
                    break;
                case 4:
                    deleteProduct(sc);
                    break;
                case 5:
                    searchNameProduct(sc);
                    break;
                case 6:
                    updateProduct(sc);
                    break;
                case 7:
                    flag = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn tử 1-7");
            }
        } while (flag);
    }

    public static void createProduct(Scanner sc) {
        System.out.println("Mời bạn nhập số lượng sản phẩm bạn cần thêm: ");
        int number = Integer.parseInt(sc.nextLine());
        System.out.println("Danh sách danh mục có sẵn:");
        for (Catalog catalog1 : catalogList) {
            System.out.println(catalog1.getCatalogId() + ". " + catalog1.getCatalogName());
        }
        for (int i = 0; i < number; i++) {
            System.out.println("Thông tin sách thứ: " + (i + 1));
            Product product = new Product();
            System.out.println("Mời bạn chọn ID danh muc: ");
            int catalogId = Integer.parseInt(sc.nextLine());
            Catalog selectedCat = catlogByID(catalogId);
            if (selectedCat != null) {
                product.setCatalog(selectedCat);
            } else {
                System.out.println("ID danh mục không hợp lệ!");
                continue;
            }
            product.inputData();
            products.add(product);
        }
    }

    public static Catalog catlogByID(int id) {
        for (Catalog catalog : catalogList) {
            if (catalog.getCatalogId() == id) {
                return catalog;
            }
        }
        return null;
    }

    public static void getAllProduct() {
        for (Product p : products) {
            p.displayData();
        }
    }

    public static void sortExprotASC() {
        products.sort(Comparator.comparingDouble(Product::getProductPrice).reversed());
        for (Product b : products) {
            b.displayData();
        }
    }

    public static void searchNameProduct(Scanner sc) {
        System.out.println("Mời bạn nhập tên san pham: ");
        String nam = sc.nextLine();
        boolean found = false;
        for (Product b : products) {
            nam = b.getProductName();
            if (b != null && b.getProductName().toLowerCase().contains(nam.toLowerCase())) {
                b.displayData();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy san pham " + nam);
        }
    }

    public static void updateProduct(Scanner sc) {
        System.out.println("Mời bạn nhập mã sản phẩm: ");
        String ma = sc.nextLine();
        for (Product p : products) {
            if (p.getProductName().equals(ma)){
                p.setProductId(ma);
                System.out.println("nhap ten");
                p.setProductName(sc.nextLine());
                System.out.println("nhap gia");
                p.setProductPrice(Double.parseDouble(sc.nextLine()));
                System.out.println("nhap mota");
                p.setDecription(sc.nextLine());
                System.out.println("nhap stock");
                p.setStock(Integer.parseInt(sc.nextLine()));
                System.out.println("nhap trang thai");
                p.setStatus(Boolean.getBoolean(sc.nextLine()));
                break;
            }
        }
    }

    public static void deleteProduct(Scanner sc) {
        System.out.println("Mời bạn nhập mã sản phẩm cần xóa: ");
        String ma = sc.nextLine();
        for (Product product : products) {
            if (product.getProductId() == ma) {
                catalogList.remove(product);
                break;
            }
        }
        System.out.println("Không tìm thấy sản phẩm với mã " + ma + " trong danh sách.");
    }
}