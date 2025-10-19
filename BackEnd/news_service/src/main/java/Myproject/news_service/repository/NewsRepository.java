package Myproject.news_service.repository;

import Myproject.news_service.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, String> {

}
