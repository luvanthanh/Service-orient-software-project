package Myproject.news_service.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int newsId;

    private String newsName; // tên
    private String newsTitle; //tiêu đề
    private String newsDate; // thời gian  ngày
    private String newsTime; //  giờ
    private String newsCategory; // loại tin
    private String newsImage;
    private String newsImage1;
    private String newsImage2;
    private String newsImage3;
    private String newsContent;
    private String newsContent1;
    private String newsContent2;
    private String newsContent3;
}
