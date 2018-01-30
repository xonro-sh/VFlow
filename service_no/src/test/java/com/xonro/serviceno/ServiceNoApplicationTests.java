package com.xonro.serviceno;

import com.xonro.serviceno.bean.custom.CustomInfo;
import com.xonro.serviceno.bean.WechatMessage;
import com.xonro.serviceno.bean.material.Article;
import com.xonro.serviceno.bean.material.NewsResponse;
import com.xonro.serviceno.bean.menu.*;
import com.xonro.serviceno.exception.WechatException;
import com.xonro.serviceno.helper.RedisHelper;
import com.xonro.serviceno.helper.ServiceNoHelper;
import com.xonro.serviceno.service.*;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceNoApplicationTests {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CustomService customService;

    @Autowired
    private MassMessageService massMessageService;

    @Autowired
    private WechatMenuService menuService;

    @Autowired
    private MaterialService materialService;
	@Test
	public void contextLoads() {
        redisHelper.set("123", "测试");
        System.out.println("进入了方法");
        String string = redisHelper.get("123").toString();
        System.err.println("string"+string);
    }

    @Test
    public void testGetAccessToken(){
        System.err.println(""+tokenService.getTokenFromCache());
        tokenService.getTokenFromCache();
    }

    @Test
    public void testBeanToXml() {
        WechatMessage imageMessage = new WechatMessage("","222", System.currentTimeMillis()/1000, "text","23333");
        //首字母转大写
        System.err.println(ServiceNoHelper.xmlInitialUppercase(ServiceNoHelper.beanToxml(imageMessage)));
    }

    @Test
    public void testWechatMessage(){
//
//        WechatMessage wechatMessage = new WechatMessage();
//        WechatArticlesMessage wechatArticlesMessage = new WechatArticlesMessage();
//        WechatArticlesMessage wechatArticlesMessage1 = new WechatArticlesMessage();
//        wechatArticlesMessage.setDescription("11111");
//        wechatArticlesMessage1.setDescription("222");
//        List<WechatArticlesMessage> wechatArticlesMessages = new ArrayList<>();
//        wechatArticlesMessages.add(wechatArticlesMessage);
//        wechatArticlesMessages.add(wechatArticlesMessage1);
//
//        System.err.println(ServiceNoHelper.beanToxml(wechatMessage).concat( ServiceNoHelper.getArticlesXml(wechatArticlesMessages)));
    }

    @Test
    public void  testCustom(){
//        customService.addCustom("test@test01", "客服1", "123456");
//        customService.updateCustom("test@test01", "客服2", "123456");
//        customService.delCustom("test@test01", "客服2", "123456");
//        customService.uploadHeadImg(ServiceNoApplicationTests.class.getClassLoader().getResource("static/image/a67388a0gy1fiv9qdwp3vj20x40xcafo.jpg").getPath(), "123", "ceshi", "test@test01");
        List<CustomInfo> customInfos = customService.getKfList();
        System.err.println(customService.getKfList());

        String result = "{" +
                "    \"kf_list\": [" +
                "        {" +
                "            \"kf_account\": \"test1@test\", " +
                "            \"kf_nick\": \"ntest1\", " +
                "            \"kf_id\": \"1001\", " +
                "            \"kf_headimgurl\": \" http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw/0\"" +
                "        }, " +
                "        {" +
                "            \"kf_account\": \"test2@test\", " +
                "            \"kf_nick\": \"ntest2\", " +
                "            \"kf_id\": \"1002\", " +
                "            \"kf_headimgurl\": \" http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw /0\"" +
                "        }, " +
                "        {" +
                "            \"kf_account\": \"test3@test\", " +
                "            \"kf_nick\": \"ntest3\", " +
                "            \"kf_id\": \"1003\", " +
                "            \"kf_headimgurl\": \" http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw /0\"" +
                "        }" +
                "    ]" +
                "}";
//        JSONObject j = JSON.parseObject(result);
//        List<CustomInfo> j1 = JSON.parseArray(j.getString("kf_list"), CustomInfo.class);
//        CustomInfo customInfo = new CustomInfo();
//        RedisConn redisConn = new RedisConn();
//        RedisConn redisConn1 = new RedisConn();
//        redisConn.setHost("1111");
//        redisConn1.setHost("1111");
//        List<RedisConn> redisConns = new ArrayList<>();
//        redisConns.add(redisConn);
//        redisConns.add(redisConn1);
//        customInfo.setKfAccount("111");
//        customInfo.setKfHeadimgurl("1111");
//        customInfo.setKfNick("111");
//        String j2 = JSON.toJSONString(customInfo);
//        System.err.println("j1"+j2);
//        CustomArticlesMessage customArticlesMessage = new CustomArticlesMessage("11","11","11","11");
//        CustomArticlesMessage customArticlesMessage1 = new CustomArticlesMessage("22","22","22","22");
//        List<CustomArticlesMessage> customArticlesMessages = new ArrayList<>();
//        customArticlesMessages.add(customArticlesMessage);
//        customArticlesMessages.add(customArticlesMessage1);
//        CustomMessageMain customMessageMain = new CustomMessageMain("11","news", customArticlesMessages, true, "21313");
//        System.err.println(JSON.toJSONString(customMessageMain));
    }

    @Test
    public void testMassMessage() throws WechatException {
//        massMessageService.sendAllByTagId(true, "", "222", "text");
//        massMessageService.sendAllByTagId(true, "", "svIC4Fs2c9wDw63VPmE5BSkSnJRsGiMm8oF6JDraRFDASpp-yNQf2d3XPINy6b-8", "mpvideo");
//        massMessageService.previewMassMessage("zz1398730142", "text", "xxxxxxxxxxxxxx");
//        massMessageService.getMassMessageState("1000000011");
        massMessageService.setMassSpeed(0);
        massMessageService.getMassSpeed();
    }

    @Test
    public void testMenu() throws WechatException {

        Menu m = new Menu("click", "测试1", "key001");
        Menu m1 = new Menu("scancode_waitmsg", "测试2", "key002");
        ViewMenu viewMenu = new ViewMenu("测试url", "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013");
//        ViewLimitedMenu viewLimitedMenu = new ViewLimitedMenu("测试图文", "");
        List<Object> list = new ArrayList<>();
        List<Object> list1 = new ArrayList<>();
        List<Object> list2 = new ArrayList<>();
        list.add(m);
        list.add(m1);
        list1.add(viewMenu);
        Menu m3 = new Menu("主菜单1", list);
        Menu m2 = new Menu("主菜单2", list1);
        list2.add(m3);
        list2.add(m2);
        MatchRule matchRule = new MatchRule();
        matchRule.setProvince("四川");
        matchRule.setCountry("中国");
        Button b = new Button(list2, matchRule);
        //创建菜单
//        menuService.menuAddConditional(b);
        //删除菜单
//        menuService.menuDel();
        //查询菜单
//        System.err.println(menuService.menuGet());
        //个性化菜单匹配
//        System.err.println(menuService.menuTryMatch("hj9315"));
        //删除个性化菜单
//        menuService.menuDelConditional("565912596");
        //查询自定义配置菜单
        System.err.println(menuService.getCurrentSelfMenu());
    }

    @Test
    public void testMaterial() throws WechatException, IOException {
        File videoFile = new File("C:/Users/user/Desktop/新建文件夹 (6)/966a74bb345344dd6a50d9b7bd518f9a.mp4");
//        System.err.println(ServiceNoApplicationTests.class.getClassLoader().getResource("static/image/966a74bb345344dd6a50d9b7bd518f9a.mp4").getPath());
        File file = new File(ServiceNoApplicationTests.class.getClassLoader().getResource("static/image/a67388a0gy1fiv9qdwp3vj20x40xcafo.jpg").getPath());
        //上传临时素材
//        System.err.println(materialService.uploadTemMedia(FileUtils.readFileToByteArray(file), "image", file.getName()));
        String mediaId = "GVszSzNE0hOiLiPNydDg_qsysCUboi6D87izofLtEFdkY-EsuZDimBH00UIXz5_9";
//        System.err.println(materialService.getTemMedia(mediaId));
        //新增永久图文素材
        List<Article> articles = new ArrayList<>();
        Article article = new Article.Builder("测试测试", "iN0DVJWsOA3313LfYphArEGSiI3I6WrMRbX_TCkTlQQ", "1", "测似乎测似乎测似乎测似乎", "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738729").build();
        articles.add(article);
        NewsResponse newsResponse = new NewsResponse(articles);
//        System.err.println(materialService.addNews(newsResponse));
        //上传图文消息内的图片获取URL
//        System.err.println(materialService.uploadImg(file));
//        System.err.println(materialService.addVideo(videoFile,"测试1","说明说明说明说明说明说明说明说明说明说明说明说明说明说明说明说明"));
//        System.err.println(materialService.delete("iN0DVJWsOA3313LfYphArKs7TrXiH3V3qWpM-Sk1-7I"));
//        System.err.println(materialService.addOthers(file, "thumb"));
        //图文永久素材
//        String newsMediaId = "iN0DVJWsOA3313LfYphArPp8lDOysXrxQ94Gkh_89Bs";
//        Article article1 = new Article.Builder("测试修改图文", "iN0DVJWsOA3313LfYphArEGSiI3I6WrMRbX_TCkTlQQ", "1", "测似乎测似乎测似乎测似乎", "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738729", "Alex", "测试修改图文").build();
//        System.err.println(materialService.updateNews(newsMediaId, "0", article1));
        //获取永久素材数量
//        System.err.println(materialService.getMaterialCount());
        //获取素材列表
        System.err.println(materialService.getMaterialBatch("image", 0, 20));
    }

}
