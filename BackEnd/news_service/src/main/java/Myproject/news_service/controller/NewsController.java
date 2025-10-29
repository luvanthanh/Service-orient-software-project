package Myproject.news_service.controller;


import Myproject.news_service.dto.request.NewsCreationRequest;
import Myproject.news_service.dto.request.NewsUpdateRequest;
import Myproject.news_service.entity.News;
import Myproject.news_service.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
@CrossOrigin(origins = "*")

public class NewsController {

    @Autowired
    private NewsService newsService;

//  lấy tất cả news
    @GetMapping
    public List<News> getProductByScreenSize(){
        return newsService.getAllNews();
    }
// lấy news by id
    @GetMapping("/{newsId}")
    public News getNewsById(@PathVariable int newsId){
        return newsService.getNewsById(newsId);
    }
// thêm tin tức mới
    @PostMapping
    public News addNews(@RequestBody NewsCreationRequest request){
        return newsService.addNews(request);
    }

//    sửa tin tức
    @PostMapping("/{newsId}")
    public News updateNews(@RequestBody NewsUpdateRequest request, @PathVariable int newsId){
        return  newsService.updateNews(request, newsId);
    }

//    xóa news
    @DeleteMapping("/{newsId}")
    public String deleteNews(@PathVariable int newsId){
        return newsService.deletedNews(newsId);
    }


}
