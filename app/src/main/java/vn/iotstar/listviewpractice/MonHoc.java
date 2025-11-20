package vn.iotstar.listviewpractice;

public class MonHoc {
    private String ten;
    private String moTa;
    private int hinhAnh;

    public MonHoc(String ten, String moTa, int hinhAnh) {
        this.ten = ten;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
    }

    public String getTen() { return ten; }
    public String getMoTa() { return moTa; }
    public int getHinhAnh() { return hinhAnh; }
}