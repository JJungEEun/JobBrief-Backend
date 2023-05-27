package co.kr.capstonemju.JobBrief.domain.news.service;

import co.kr.capstonemju.JobBrief.domain.news.controller.dto.NewsDto;
import co.kr.capstonemju.JobBrief.domain.news.controller.dto.NewsListDto;
import co.kr.capstonemju.JobBrief.domain.news.model.Job;
import co.kr.capstonemju.JobBrief.domain.news.model.News;
import co.kr.capstonemju.JobBrief.domain.news.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NewsService {
    private final NewsRepository newsRepository;

    public NewsListDto getNewsList(String job) {
        List<News> newsList = new ArrayList<>();
        switch (job) {
            case "all"-> newsList = newsRepository.findAllByOrderByPub_dateDesc();
            case "production-quality" -> newsList = newsRepository.findByJob(Job.PRODUCTION_QUALITY);
            case "it-developer" -> newsList = newsRepository.findByJob(Job.IT_DEVELOPMENT);
            case "human-affairs" -> newsList = newsRepository.findByJob(Job.HUMAN_AFFAIRS);
            case "finance-accounting" -> newsList = newsRepository.findByJob(Job.FINANCE_ACCOUNTING);
            case "strategy-planning" -> newsList = newsRepository.findByJob(Job.STRATEGY_PLANNING);
            case "sales-management" -> newsList = newsRepository.findByJob(Job.SALES_MANAGEMENT);
            case "marketing-merchandiser" -> newsList = newsRepository.findByJob(Job.MARKETING_MERCHANDISER);
            default -> System.out.println("올바르지 않은 값이 들어왔습니다");
        }
        List<NewsDto> newsDtoList = newsList.stream().map(NewsDto::new).toList();
        return new NewsListDto(newsDtoList);
    }
}
