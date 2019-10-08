package com.oracle.springboot.cache;

import com.oracle.springboot.bean.TagModel;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static  List<TagModel> get(){
        List<TagModel> tagModelList=new ArrayList<>();

        TagModel program = new TagModel();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("javascript", "php", "css", "html", "html5", "java", "node.js", "python", "c", "golang", "objective-c", "typescript", "shell", "swift", "c#", "sass", "ruby", "bash", "less", "asp.net", "lua", "scala", "coffeescript", "actionscript", "rust", "erlang", "perl"));
        tagModelList.add(program);

        TagModel framework = new TagModel();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("laravel", "spring", "express", "django", "flask", "yii", "ruby-on-rails", "tornado", "koa", "struts"));
        tagModelList.add(framework);

        TagModel server = new TagModel();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux", "nginx", "docker", "apache", "ubuntu", "centos", "缓存 tomcat", "负载均衡", "unix", "hadoop", "windows-server"));
        tagModelList.add(server);

        TagModel db = new TagModel();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql", "redis", "mongodb", "sql", "oracle", "nosql memcached", "sqlserver", "postgresql", "sqlite"));
        tagModelList.add(db);

        TagModel tool = new TagModel();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("git", "github", "visual-studio-code", "vim", "sublime-text", "xcode intellij-idea", "eclipse", "maven", "ide", "svn", "visual-studio", "atom emacs", "textmate", "hg"));
        tagModelList.add(tool);

        TagModel rq = new TagModel();
        rq.setCategoryName("人情世故");
        rq.setTags(Arrays.asList("价值观", "人生观","世界观"));
        tagModelList.add(rq);

        return tagModelList;


    }


    public static String filterInvalid(String tags) {
        String[] split = StringUtils.split(tags, ",");
        List<TagModel> tagModelList = get();

        List<String> tagList = tagModelList.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> StringUtils.isBlank(t) || !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }

    public static void main(String[] args) {
        int i = (5 - 1) >>> 1;
        System.out.println(i);
    }
}
