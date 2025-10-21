package Myproject.news_service.service;

import Myproject.news_service.dto.request.NewsCreationRequest;
import Myproject.news_service.dto.request.NewsUpdateRequest;
import Myproject.news_service.entity.News;
import Myproject.news_service.mapper.NewsMapper;
import Myproject.news_service.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsMapper newsMapper;

    public List<News> getAllNews(){
        return newsRepository.findAll();
    }

//    get news bằng id
    public News getNewsById(int id){
        return newsRepository.getNewsByNewsId(id);
    }

    public News addNews(NewsCreationRequest request){
        News news = new News();
        news =newsMapper.toNews(request);
        newsRepository.save(news);
        return news;
    }

    public News  updateNews(NewsUpdateRequest request, int id){
        News news = getNewsById(id);
        news = newsMapper.toNewsUpdate(request);
        return newsRepository.save(news);
    }


    public String deletedNews(int newsId){
        newsRepository.deleteByNewsId(newsId);
        return  "news has deleted";
    }
}
