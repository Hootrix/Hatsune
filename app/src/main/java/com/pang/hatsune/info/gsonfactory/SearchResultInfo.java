package com.pang.hatsune.info.gsonfactory;

import com.pang.hatsune.interface_abstract.Info;

import java.util.List;

/**
 * 搜索结果的信息封装类。 搜索接口来自link站
 * Created by Administrator on 2016/8/8.
 */
public class SearchResultInfo implements Info{

    /**
     * state : 1
     * message : success
     * result : {"data":[{"id":"853707","name":"小sa神「小幸运」 ukulele弹唱","length":"581","pic":"http://echo-image.qiniucdn.com/Fhlyc2WiqtlssoCrWk5rUB_6ieyG","channel_id":"214","user_id":"3507246","source":"http://7u2q8y.com2.z0.glb.qiniucdn.com/c1/47ed3b452bea311b39b5754dc36f5ceb36e349c6199ac8d87f50bb7badb57ca1a2f8516a.mp3?1449110051","status_mask":"32","commend_time":"1447761600","status":"1","like_count":"26234","exchange_count":"23122","comment_count":"1190","view_count":1772586,"is_edit":"1","is_pay":0,"info":"来自电影《我的少女时代》主题曲《小幸运－田馥甄Hebe》\r\n\r\n我听见雨滴 落在青青草地\r\n\r\n我听","is_like":0,"pic_100":"http://echo-image.qiniucdn.com/Fhlyc2WiqtlssoCrWk5rUB_6ieyG?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/100/q/100","pic_200":"http://echo-image.qiniucdn.com/Fhlyc2WiqtlssoCrWk5rUB_6ieyG?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/200/q/100","pic_500":"http://echo-image.qiniucdn.com/Fhlyc2WiqtlssoCrWk5rUB_6ieyG?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/500/q/100","pic_640":"http://echo-image.qiniucdn.com/Fhlyc2WiqtlssoCrWk5rUB_6ieyG?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/640/q/100","pic_750":"http://echo-image.qiniucdn.com/Fhlyc2WiqtlssoCrWk5rUB_6ieyG?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/750/q/100","pic_1080":"http://echo-image.qiniucdn.com/Fhlyc2WiqtlssoCrWk5rUB_6ieyG?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/1080/q/100","is_bought":1},{"id":"803307","name":"2014年中文流行歌曲混音","length":"414","pic":"http://echo-image.qiniucdn.com/Fr4asVTs1VekPTaWoxXp-9ydn9qj","channel_id":"1131","user_id":"115289","source":"http://7u2q8y.com2.z0.glb.qiniucdn.com/c1/88ccb55dee47fd7a94164e49b5ffb935a98412f5eb487975d8b266f7031dd1a75501afda.mp3?1448920952","status_mask":"0","commend_time":"1443600000","status":"1","like_count":"11162","exchange_count":"7223","comment_count":"797","view_count":462279,"is_edit":"1","is_pay":0,"info":"以前都是发的一些欧美流行歌曲串烧。今天来一发中文串烧混音。希望大家喜欢\r\n\r\n歌单如下：\r\n\r\n梁文","is_like":0,"pic_100":"http://echo-image.qiniucdn.com/Fr4asVTs1VekPTaWoxXp-9ydn9qj?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/100/q/100","pic_200":"http://echo-image.qiniucdn.com/Fr4asVTs1VekPTaWoxXp-9ydn9qj?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/200/q/100","pic_500":"http://echo-image.qiniucdn.com/Fr4asVTs1VekPTaWoxXp-9ydn9qj?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/500/q/100","pic_640":"http://echo-image.qiniucdn.com/Fr4asVTs1VekPTaWoxXp-9ydn9qj?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/640/q/100","pic_750":"http://echo-image.qiniucdn.com/Fr4asVTs1VekPTaWoxXp-9ydn9qj?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/750/q/100","pic_1080":"http://echo-image.qiniucdn.com/Fr4asVTs1VekPTaWoxXp-9ydn9qj?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/1080/q/100","is_bought":1},{"id":"224564","name":"2014中文流行混音top25","length":"385","pic":"http://echo-web-pic.b0.upaiyun.com/1ee1769ea51ea4a161c5ed755e7d2770.jpg","channel_id":"1131","user_id":"24600","source":"http://7u2q8y.com2.z0.glb.qiniucdn.com/c1/395c0e9fad4f001075ae34ac40c236de11c8702ab6114132b14bbb6ca42e9b2d1d0ecdf7.mp3?1448194932","status_mask":"0","commend_time":"1421319102","status":"1","like_count":"4753","exchange_count":"2202","comment_count":"1370","view_count":179233,"is_edit":"1","is_pay":0,"info":"华语乐坛的第一个年终混音作品，历史性的一刻！以后每年不用只等听老外的什么Pop Danthology","is_like":0,"pic_100":"http://echo-web-pic.b0.upaiyun.com/1ee1769ea51ea4a161c5ed755e7d2770.jpg!100","pic_200":"http://echo-web-pic.b0.upaiyun.com/1ee1769ea51ea4a161c5ed755e7d2770.jpg!200","pic_500":"http://echo-web-pic.b0.upaiyun.com/1ee1769ea51ea4a161c5ed755e7d2770.jpg!500","pic_640":"http://echo-web-pic.b0.upaiyun.com/1ee1769ea51ea4a161c5ed755e7d2770.jpg!640","pic_750":"http://echo-web-pic.b0.upaiyun.com/1ee1769ea51ea4a161c5ed755e7d2770.jpg!750","pic_1080":"http://echo-web-pic.b0.upaiyun.com/1ee1769ea51ea4a161c5ed755e7d2770.jpg!1080","is_bought":1},{"id":"950159","name":"小幸運 粵語版 《我的少女時代主題曲》小幸运 粤语","length":"279","pic":"http://echo-image.qiniucdn.com/FvZM30CwMPpftDB41hAB3uUNidFK","channel_id":"24","user_id":"1531066","source":"http://7u2q8y.com2.z0.glb.qiniucdn.com/c1/d61c72a0ab0ab00a06c1a1a684be19c3402cd8524b0271a4de39706bee20eb8df211b7cf.mp3?1447252151","status_mask":"0","commend_time":"0","status":"1","like_count":"2843","exchange_count":"4195","comment_count":"81","view_count":213011,"is_edit":"0","is_pay":0,"info":"YouTube大神翻唱hebe小幸運 粵語版\r\n--------------------------","is_like":0,"pic_100":"http://echo-image.qiniucdn.com/FvZM30CwMPpftDB41hAB3uUNidFK?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/100/q/100","pic_200":"http://echo-image.qiniucdn.com/FvZM30CwMPpftDB41hAB3uUNidFK?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/200/q/100","pic_500":"http://echo-image.qiniucdn.com/FvZM30CwMPpftDB41hAB3uUNidFK?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/500/q/100","pic_640":"http://echo-image.qiniucdn.com/FvZM30CwMPpftDB41hAB3uUNidFK?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/640/q/100","pic_750":"http://echo-image.qiniucdn.com/FvZM30CwMPpftDB41hAB3uUNidFK?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/750/q/100","pic_1080":"http://echo-image.qiniucdn.com/FvZM30CwMPpftDB41hAB3uUNidFK?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/1080/q/100","is_bought":1},{"id":"984638","name":"小幸运----周俊涛（男版）","length":"265","pic":"http://echo-image.qiniucdn.com/FtlB7qAm6xSgnK2TOJi_1XIZC3ib","channel_id":"214","user_id":"219946","source":"http://7u2q8y.com2.z0.glb.qiniucdn.com/c1/7537cc28d0c0cc7b7feb72981dd63b7764c487fca8196e7077d344cd1ddc4643ca8badf9.mp3?1462632722","status_mask":"16","commend_time":"0","status":"1","like_count":"1139","exchange_count":"908","comment_count":"108","view_count":74096,"is_edit":"0","is_pay":0,"info":"这首歌刚出的时候就放进手机里面了，因为hebe是我的女神啊！小时候追SHE追的这么疯狂，不过现在要理","is_like":0,"pic_100":"http://echo-image.qiniucdn.com/FtlB7qAm6xSgnK2TOJi_1XIZC3ib?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/100/q/100","pic_200":"http://echo-image.qiniucdn.com/FtlB7qAm6xSgnK2TOJi_1XIZC3ib?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/200/q/100","pic_500":"http://echo-image.qiniucdn.com/FtlB7qAm6xSgnK2TOJi_1XIZC3ib?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/500/q/100","pic_640":"http://echo-image.qiniucdn.com/FtlB7qAm6xSgnK2TOJi_1XIZC3ib?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/640/q/100","pic_750":"http://echo-image.qiniucdn.com/FtlB7qAm6xSgnK2TOJi_1XIZC3ib?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/750/q/100","pic_1080":"http://echo-image.qiniucdn.com/FtlB7qAm6xSgnK2TOJi_1XIZC3ib?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/1080/q/100","is_bought":1},{"id":"636311","name":"离岛-田馥甄","length":"215","pic":"http://echo-mx.b0.upaiyun.com/1769737207.jpg","channel_id":"24","user_id":"4582422","source":"http://7u2q8y.com2.z0.glb.qiniucdn.com/c1/1e3643b94862298b599c3dcfb280cc00b4f33c6194d6b5a19ad535d4ead161d26ae79b64.mp3?1448478128","status_mask":"0","commend_time":"0","status":"1","like_count":"484","exchange_count":"279","comment_count":"17","view_count":14282,"is_edit":"0","is_pay":0,"info":"To Hebe Hebe田馥甄-《离岛》\n词-施人诚 曲-林一峰\n编曲-卢家宏 制作人-王治平\n专辑","is_like":0,"pic_100":"http://echo-mx.b0.upaiyun.com/1769737207.jpg!100","pic_200":"http://echo-mx.b0.upaiyun.com/1769737207.jpg!200","pic_500":"http://echo-mx.b0.upaiyun.com/1769737207.jpg!500","pic_640":"http://echo-mx.b0.upaiyun.com/1769737207.jpg!640","pic_750":"http://echo-mx.b0.upaiyun.com/1769737207.jpg!750","pic_1080":"http://echo-mx.b0.upaiyun.com/1769737207.jpg!1080","is_bought":1},{"id":"960308","name":"Panther Chan 陈蕾-小幸运  吉他弹唱","length":"288","pic":"http://echo-image.qiniucdn.com/FkHbjDrFy9yMV8F8pD4q-ieIvneN","channel_id":"214","user_id":"1317958","source":"http://7u2q8y.com2.z0.glb.qiniucdn.com/c1/a4d4aad777e9ca51db1c69171bca3570b9fffca94d60771d0c893295eb9513d67e2d7bad.mp3?1447769489","status_mask":"0","commend_time":"0","status":"1","like_count":"524","exchange_count":"335","comment_count":"32","view_count":29869,"is_edit":"0","is_pay":0,"info":"#HEBE田馥甄# #小幸运#  期待这部剧ing~~~~~~","is_like":0,"pic_100":"http://echo-image.qiniucdn.com/FkHbjDrFy9yMV8F8pD4q-ieIvneN?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/100/q/100","pic_200":"http://echo-image.qiniucdn.com/FkHbjDrFy9yMV8F8pD4q-ieIvneN?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/200/q/100","pic_500":"http://echo-image.qiniucdn.com/FkHbjDrFy9yMV8F8pD4q-ieIvneN?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/500/q/100","pic_640":"http://echo-image.qiniucdn.com/FkHbjDrFy9yMV8F8pD4q-ieIvneN?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/640/q/100","pic_750":"http://echo-image.qiniucdn.com/FkHbjDrFy9yMV8F8pD4q-ieIvneN?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/750/q/100","pic_1080":"http://echo-image.qiniucdn.com/FkHbjDrFy9yMV8F8pD4q-ieIvneN?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/1080/q/100","is_bought":1},{"id":"1024170","name":"「小幸运」钢琴深情改编","length":"276","pic":"http://7xavig.com2.z0.glb.qiniucdn.com/a0a1e45bb6921f2ff9d55691fd9af85e.png","channel_id":"1162","user_id":"4635644","source":"http://7u2q8y.com2.z0.glb.qiniucdn.com/c1/cea3494b52b6966f895ce0cd5783afe7497430e14b1cd46420a591f7fed6d3e9699db36e.mp3?1451527884","status_mask":"32","commend_time":"0","status":"1","like_count":"317","exchange_count":"153","comment_count":"15","view_count":11584,"is_edit":"0","is_pay":0,"info":"遇见你，是我的小幸运\n\n原唱 Hebe\n改编 Phounius\n\n我听见雨滴落在青青草地\r\n我听见远","is_like":0,"pic_100":"http://7xavig.com2.z0.glb.qiniucdn.com/a0a1e45bb6921f2ff9d55691fd9af85e.png?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/100/q/100","pic_200":"http://7xavig.com2.z0.glb.qiniucdn.com/a0a1e45bb6921f2ff9d55691fd9af85e.png?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/200/q/100","pic_500":"http://7xavig.com2.z0.glb.qiniucdn.com/a0a1e45bb6921f2ff9d55691fd9af85e.png?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/500/q/100","pic_640":"http://7xavig.com2.z0.glb.qiniucdn.com/a0a1e45bb6921f2ff9d55691fd9af85e.png?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/640/q/100","pic_750":"http://7xavig.com2.z0.glb.qiniucdn.com/a0a1e45bb6921f2ff9d55691fd9af85e.png?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/750/q/100","pic_1080":"http://7xavig.com2.z0.glb.qiniucdn.com/a0a1e45bb6921f2ff9d55691fd9af85e.png?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/1080/q/100","is_bought":1},{"id":"1112899","name":"不醉不会【男声Cover】","length":"251","pic":"http://7xavig.com2.z0.glb.qiniucdn.com/933a47e8f2e91d94aca67f9764fcfe84.png","channel_id":"214","user_id":"3475958","source":"http://7u2q8y.com2.z0.glb.qiniucdn.com/c1/809843e20e9a6d63c6f54ebec3f859da353ec9a50a9c2852e390bfad8fc35e228a22392d.mp3?1456538093","status_mask":"16","commend_time":"0","status":"1","like_count":"344","exchange_count":"298","comment_count":"26","view_count":23053,"is_edit":"0","is_pay":0,"info":"啊啦啦终于和人帅歌甜气质佳的顾老师合作 开心开心开心\n\n不醉不会\n作曲：刘大江\n作词：林夕\n原唱：H","is_like":0,"pic_100":"http://7xavig.com2.z0.glb.qiniucdn.com/933a47e8f2e91d94aca67f9764fcfe84.png?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/100/q/100","pic_200":"http://7xavig.com2.z0.glb.qiniucdn.com/933a47e8f2e91d94aca67f9764fcfe84.png?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/200/q/100","pic_500":"http://7xavig.com2.z0.glb.qiniucdn.com/933a47e8f2e91d94aca67f9764fcfe84.png?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/500/q/100","pic_640":"http://7xavig.com2.z0.glb.qiniucdn.com/933a47e8f2e91d94aca67f9764fcfe84.png?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/640/q/100","pic_750":"http://7xavig.com2.z0.glb.qiniucdn.com/933a47e8f2e91d94aca67f9764fcfe84.png?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/750/q/100","pic_1080":"http://7xavig.com2.z0.glb.qiniucdn.com/933a47e8f2e91d94aca67f9764fcfe84.png?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/1080/q/100","is_bought":1}],"soundCount":10,"user":[],"channel":[],"topic":[],"hotkeywords":[],"keys":["hebe","HeunMoooo_be"],"keyword":"hebe","suggestionwords":[]}
     */

    private int state;
    private String message;
    /**
     * data : [{"id":"853707","name":"小sa神「小幸运」 ukulele弹唱","length":"581","pic":"http://echo-image.qiniucdn.com/Fhlyc2WiqtlssoCrWk5rUB_6ieyG","channel_id":"214","user_id":"3507246","source":"http://7u2q8y.com2.z0.glb.qiniucdn.com/c1/47ed3b452bea311b39b5754dc36f5ceb36e349c6199ac8d87f50bb7badb57ca1a2f8516a.mp3?1449110051","status_mask":"32","commend_time":"1447761600","status":"1","like_count":"26234","exchange_count":"23122","comment_count":"1190","view_count":1772586,"is_edit":"1","is_pay":0,"info":"来自电影《我的少女时代》主题曲《小幸运－田馥甄Hebe》\r\n\r\n我听见雨滴 落在青青草地\r\n\r\n我听","is_like":0,"pic_100":"http://echo-image.qiniucdn.com/Fhlyc2WiqtlssoCrWk5rUB_6ieyG?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/100/q/100","pic_200":"http://echo-image.qiniucdn.com/Fhlyc2WiqtlssoCrWk5rUB_6ieyG?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/200/q/100","pic_500":"http://echo-image.qiniucdn.com/Fhlyc2WiqtlssoCrWk5rUB_6ieyG?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/500/q/100","pic_640":"http://echo-image.qiniucdn.com/Fhlyc2WiqtlssoCrWk5rUB_6ieyG?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/640/q/100","pic_750":"http://echo-image.qiniucdn.com/Fhlyc2WiqtlssoCrWk5rUB_6ieyG?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/750/q/100","pic_1080":"http://echo-image.qiniucdn.com/Fhlyc2WiqtlssoCrWk5rUB_6ieyG?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/1080/q/100","is_bought":1},{"id":"803307","name":"2014年中文流行歌曲混音","length":"414","pic":"http://echo-image.qiniucdn.com/Fr4asVTs1VekPTaWoxXp-9ydn9qj","channel_id":"1131","user_id":"115289","source":"http://7u2q8y.com2.z0.glb.qiniucdn.com/c1/88ccb55dee47fd7a94164e49b5ffb935a98412f5eb487975d8b266f7031dd1a75501afda.mp3?1448920952","status_mask":"0","commend_time":"1443600000","status":"1","like_count":"11162","exchange_count":"7223","comment_count":"797","view_count":462279,"is_edit":"1","is_pay":0,"info":"以前都是发的一些欧美流行歌曲串烧。今天来一发中文串烧混音。希望大家喜欢\r\n\r\n歌单如下：\r\n\r\n梁文","is_like":0,"pic_100":"http://echo-image.qiniucdn.com/Fr4asVTs1VekPTaWoxXp-9ydn9qj?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/100/q/100","pic_200":"http://echo-image.qiniucdn.com/Fr4asVTs1VekPTaWoxXp-9ydn9qj?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/200/q/100","pic_500":"http://echo-image.qiniucdn.com/Fr4asVTs1VekPTaWoxXp-9ydn9qj?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/500/q/100","pic_640":"http://echo-image.qiniucdn.com/Fr4asVTs1VekPTaWoxXp-9ydn9qj?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/640/q/100","pic_750":"http://echo-image.qiniucdn.com/Fr4asVTs1VekPTaWoxXp-9ydn9qj?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/750/q/100","pic_1080":"http://echo-image.qiniucdn.com/Fr4asVTs1VekPTaWoxXp-9ydn9qj?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/1080/q/100","is_bought":1},{"id":"224564","name":"2014中文流行混音top25","length":"385","pic":"http://echo-web-pic.b0.upaiyun.com/1ee1769ea51ea4a161c5ed755e7d2770.jpg","channel_id":"1131","user_id":"24600","source":"http://7u2q8y.com2.z0.glb.qiniucdn.com/c1/395c0e9fad4f001075ae34ac40c236de11c8702ab6114132b14bbb6ca42e9b2d1d0ecdf7.mp3?1448194932","status_mask":"0","commend_time":"1421319102","status":"1","like_count":"4753","exchange_count":"2202","comment_count":"1370","view_count":179233,"is_edit":"1","is_pay":0,"info":"华语乐坛的第一个年终混音作品，历史性的一刻！以后每年不用只等听老外的什么Pop Danthology","is_like":0,"pic_100":"http://echo-web-pic.b0.upaiyun.com/1ee1769ea51ea4a161c5ed755e7d2770.jpg!100","pic_200":"http://echo-web-pic.b0.upaiyun.com/1ee1769ea51ea4a161c5ed755e7d2770.jpg!200","pic_500":"http://echo-web-pic.b0.upaiyun.com/1ee1769ea51ea4a161c5ed755e7d2770.jpg!500","pic_640":"http://echo-web-pic.b0.upaiyun.com/1ee1769ea51ea4a161c5ed755e7d2770.jpg!640","pic_750":"http://echo-web-pic.b0.upaiyun.com/1ee1769ea51ea4a161c5ed755e7d2770.jpg!750","pic_1080":"http://echo-web-pic.b0.upaiyun.com/1ee1769ea51ea4a161c5ed755e7d2770.jpg!1080","is_bought":1},{"id":"950159","name":"小幸運 粵語版 《我的少女時代主題曲》小幸运 粤语","length":"279","pic":"http://echo-image.qiniucdn.com/FvZM30CwMPpftDB41hAB3uUNidFK","channel_id":"24","user_id":"1531066","source":"http://7u2q8y.com2.z0.glb.qiniucdn.com/c1/d61c72a0ab0ab00a06c1a1a684be19c3402cd8524b0271a4de39706bee20eb8df211b7cf.mp3?1447252151","status_mask":"0","commend_time":"0","status":"1","like_count":"2843","exchange_count":"4195","comment_count":"81","view_count":213011,"is_edit":"0","is_pay":0,"info":"YouTube大神翻唱hebe小幸運 粵語版\r\n--------------------------","is_like":0,"pic_100":"http://echo-image.qiniucdn.com/FvZM30CwMPpftDB41hAB3uUNidFK?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/100/q/100","pic_200":"http://echo-image.qiniucdn.com/FvZM30CwMPpftDB41hAB3uUNidFK?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/200/q/100","pic_500":"http://echo-image.qiniucdn.com/FvZM30CwMPpftDB41hAB3uUNidFK?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/500/q/100","pic_640":"http://echo-image.qiniucdn.com/FvZM30CwMPpftDB41hAB3uUNidFK?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/640/q/100","pic_750":"http://echo-image.qiniucdn.com/FvZM30CwMPpftDB41hAB3uUNidFK?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/750/q/100","pic_1080":"http://echo-image.qiniucdn.com/FvZM30CwMPpftDB41hAB3uUNidFK?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/1080/q/100","is_bought":1},{"id":"984638","name":"小幸运----周俊涛（男版）","length":"265","pic":"http://echo-image.qiniucdn.com/FtlB7qAm6xSgnK2TOJi_1XIZC3ib","channel_id":"214","user_id":"219946","source":"http://7u2q8y.com2.z0.glb.qiniucdn.com/c1/7537cc28d0c0cc7b7feb72981dd63b7764c487fca8196e7077d344cd1ddc4643ca8badf9.mp3?1462632722","status_mask":"16","commend_time":"0","status":"1","like_count":"1139","exchange_count":"908","comment_count":"108","view_count":74096,"is_edit":"0","is_pay":0,"info":"这首歌刚出的时候就放进手机里面了，因为hebe是我的女神啊！小时候追SHE追的这么疯狂，不过现在要理","is_like":0,"pic_100":"http://echo-image.qiniucdn.com/FtlB7qAm6xSgnK2TOJi_1XIZC3ib?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/100/q/100","pic_200":"http://echo-image.qiniucdn.com/FtlB7qAm6xSgnK2TOJi_1XIZC3ib?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/200/q/100","pic_500":"http://echo-image.qiniucdn.com/FtlB7qAm6xSgnK2TOJi_1XIZC3ib?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/500/q/100","pic_640":"http://echo-image.qiniucdn.com/FtlB7qAm6xSgnK2TOJi_1XIZC3ib?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/640/q/100","pic_750":"http://echo-image.qiniucdn.com/FtlB7qAm6xSgnK2TOJi_1XIZC3ib?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/750/q/100","pic_1080":"http://echo-image.qiniucdn.com/FtlB7qAm6xSgnK2TOJi_1XIZC3ib?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/1080/q/100","is_bought":1},{"id":"636311","name":"离岛-田馥甄","length":"215","pic":"http://echo-mx.b0.upaiyun.com/1769737207.jpg","channel_id":"24","user_id":"4582422","source":"http://7u2q8y.com2.z0.glb.qiniucdn.com/c1/1e3643b94862298b599c3dcfb280cc00b4f33c6194d6b5a19ad535d4ead161d26ae79b64.mp3?1448478128","status_mask":"0","commend_time":"0","status":"1","like_count":"484","exchange_count":"279","comment_count":"17","view_count":14282,"is_edit":"0","is_pay":0,"info":"To Hebe Hebe田馥甄-《离岛》\n词-施人诚 曲-林一峰\n编曲-卢家宏 制作人-王治平\n专辑","is_like":0,"pic_100":"http://echo-mx.b0.upaiyun.com/1769737207.jpg!100","pic_200":"http://echo-mx.b0.upaiyun.com/1769737207.jpg!200","pic_500":"http://echo-mx.b0.upaiyun.com/1769737207.jpg!500","pic_640":"http://echo-mx.b0.upaiyun.com/1769737207.jpg!640","pic_750":"http://echo-mx.b0.upaiyun.com/1769737207.jpg!750","pic_1080":"http://echo-mx.b0.upaiyun.com/1769737207.jpg!1080","is_bought":1},{"id":"960308","name":"Panther Chan 陈蕾-小幸运  吉他弹唱","length":"288","pic":"http://echo-image.qiniucdn.com/FkHbjDrFy9yMV8F8pD4q-ieIvneN","channel_id":"214","user_id":"1317958","source":"http://7u2q8y.com2.z0.glb.qiniucdn.com/c1/a4d4aad777e9ca51db1c69171bca3570b9fffca94d60771d0c893295eb9513d67e2d7bad.mp3?1447769489","status_mask":"0","commend_time":"0","status":"1","like_count":"524","exchange_count":"335","comment_count":"32","view_count":29869,"is_edit":"0","is_pay":0,"info":"#HEBE田馥甄# #小幸运#  期待这部剧ing~~~~~~","is_like":0,"pic_100":"http://echo-image.qiniucdn.com/FkHbjDrFy9yMV8F8pD4q-ieIvneN?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/100/q/100","pic_200":"http://echo-image.qiniucdn.com/FkHbjDrFy9yMV8F8pD4q-ieIvneN?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/200/q/100","pic_500":"http://echo-image.qiniucdn.com/FkHbjDrFy9yMV8F8pD4q-ieIvneN?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/500/q/100","pic_640":"http://echo-image.qiniucdn.com/FkHbjDrFy9yMV8F8pD4q-ieIvneN?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/640/q/100","pic_750":"http://echo-image.qiniucdn.com/FkHbjDrFy9yMV8F8pD4q-ieIvneN?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/750/q/100","pic_1080":"http://echo-image.qiniucdn.com/FkHbjDrFy9yMV8F8pD4q-ieIvneN?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/1080/q/100","is_bought":1},{"id":"1024170","name":"「小幸运」钢琴深情改编","length":"276","pic":"http://7xavig.com2.z0.glb.qiniucdn.com/a0a1e45bb6921f2ff9d55691fd9af85e.png","channel_id":"1162","user_id":"4635644","source":"http://7u2q8y.com2.z0.glb.qiniucdn.com/c1/cea3494b52b6966f895ce0cd5783afe7497430e14b1cd46420a591f7fed6d3e9699db36e.mp3?1451527884","status_mask":"32","commend_time":"0","status":"1","like_count":"317","exchange_count":"153","comment_count":"15","view_count":11584,"is_edit":"0","is_pay":0,"info":"遇见你，是我的小幸运\n\n原唱 Hebe\n改编 Phounius\n\n我听见雨滴落在青青草地\r\n我听见远","is_like":0,"pic_100":"http://7xavig.com2.z0.glb.qiniucdn.com/a0a1e45bb6921f2ff9d55691fd9af85e.png?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/100/q/100","pic_200":"http://7xavig.com2.z0.glb.qiniucdn.com/a0a1e45bb6921f2ff9d55691fd9af85e.png?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/200/q/100","pic_500":"http://7xavig.com2.z0.glb.qiniucdn.com/a0a1e45bb6921f2ff9d55691fd9af85e.png?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/500/q/100","pic_640":"http://7xavig.com2.z0.glb.qiniucdn.com/a0a1e45bb6921f2ff9d55691fd9af85e.png?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/640/q/100","pic_750":"http://7xavig.com2.z0.glb.qiniucdn.com/a0a1e45bb6921f2ff9d55691fd9af85e.png?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/750/q/100","pic_1080":"http://7xavig.com2.z0.glb.qiniucdn.com/a0a1e45bb6921f2ff9d55691fd9af85e.png?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/1080/q/100","is_bought":1},{"id":"1112899","name":"不醉不会【男声Cover】","length":"251","pic":"http://7xavig.com2.z0.glb.qiniucdn.com/933a47e8f2e91d94aca67f9764fcfe84.png","channel_id":"214","user_id":"3475958","source":"http://7u2q8y.com2.z0.glb.qiniucdn.com/c1/809843e20e9a6d63c6f54ebec3f859da353ec9a50a9c2852e390bfad8fc35e228a22392d.mp3?1456538093","status_mask":"16","commend_time":"0","status":"1","like_count":"344","exchange_count":"298","comment_count":"26","view_count":23053,"is_edit":"0","is_pay":0,"info":"啊啦啦终于和人帅歌甜气质佳的顾老师合作 开心开心开心\n\n不醉不会\n作曲：刘大江\n作词：林夕\n原唱：H","is_like":0,"pic_100":"http://7xavig.com2.z0.glb.qiniucdn.com/933a47e8f2e91d94aca67f9764fcfe84.png?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/100/q/100","pic_200":"http://7xavig.com2.z0.glb.qiniucdn.com/933a47e8f2e91d94aca67f9764fcfe84.png?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/200/q/100","pic_500":"http://7xavig.com2.z0.glb.qiniucdn.com/933a47e8f2e91d94aca67f9764fcfe84.png?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/500/q/100","pic_640":"http://7xavig.com2.z0.glb.qiniucdn.com/933a47e8f2e91d94aca67f9764fcfe84.png?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/640/q/100","pic_750":"http://7xavig.com2.z0.glb.qiniucdn.com/933a47e8f2e91d94aca67f9764fcfe84.png?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/750/q/100","pic_1080":"http://7xavig.com2.z0.glb.qiniucdn.com/933a47e8f2e91d94aca67f9764fcfe84.png?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/1080/q/100","is_bought":1}]
     * soundCount : 10
     * user : []
     * channel : []
     * topic : []
     * hotkeywords : []
     * keys : ["hebe","HeunMoooo_be"]
     * keyword : hebe
     * suggestionwords : []
     */

    private ResultBean result;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private int soundCount;
        private String keyword;
        /**
         * id : 853707
         * name : 小sa神「小幸运」 ukulele弹唱
         * length : 581
         * pic : http://echo-image.qiniucdn.com/Fhlyc2WiqtlssoCrWk5rUB_6ieyG
         * channel_id : 214
         * user_id : 3507246
         * source : http://7u2q8y.com2.z0.glb.qiniucdn.com/c1/47ed3b452bea311b39b5754dc36f5ceb36e349c6199ac8d87f50bb7badb57ca1a2f8516a.mp3?1449110051
         * status_mask : 32
         * commend_time : 1447761600
         * status : 1
         * like_count : 26234
         * exchange_count : 23122
         * comment_count : 1190
         * view_count : 1772586
         * is_edit : 1
         * is_pay : 0
         * info : 来自电影《我的少女时代》主题曲《小幸运－田馥甄Hebe》

         我听见雨滴 落在青青草地

         我听
         * is_like : 0
         * pic_100 : http://echo-image.qiniucdn.com/Fhlyc2WiqtlssoCrWk5rUB_6ieyG?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/100/q/100
         * pic_200 : http://echo-image.qiniucdn.com/Fhlyc2WiqtlssoCrWk5rUB_6ieyG?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/200/q/100
         * pic_500 : http://echo-image.qiniucdn.com/Fhlyc2WiqtlssoCrWk5rUB_6ieyG?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/500/q/100
         * pic_640 : http://echo-image.qiniucdn.com/Fhlyc2WiqtlssoCrWk5rUB_6ieyG?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/640/q/100
         * pic_750 : http://echo-image.qiniucdn.com/Fhlyc2WiqtlssoCrWk5rUB_6ieyG?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/750/q/100
         * pic_1080 : http://echo-image.qiniucdn.com/Fhlyc2WiqtlssoCrWk5rUB_6ieyG?imageMogr2/auto-orient/quality/100%7CimageView2/0/w/1080/q/100
         * is_bought : 1
         */

        private List<DataBean> data;
        private List<String> keys;
        private List<?> suggestionwords;

        public int getSoundCount() {
            return soundCount;
        }

        public void setSoundCount(int soundCount) {
            this.soundCount = soundCount;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public List<String> getKeys() {
            return keys;
        }

        public void setKeys(List<String> keys) {
            this.keys = keys;
        }

        public List<?> getSuggestionwords() {
            return suggestionwords;
        }

        public void setSuggestionwords(List<?> suggestionwords) {
            this.suggestionwords = suggestionwords;
        }

        public static class DataBean {
            private String id;
            private String name;
            private String length;
            private String pic;
            private String channel_id;
            private String user_id;
            private String source;
            private String status_mask;
            private String commend_time;
            private String status;
            private String like_count;
            private String exchange_count;
            private String comment_count;
            private int view_count;
            private String is_edit;
            private int is_pay;
            private String info;
            private int is_like;
            private String pic_100;
            private String pic_200;
            private String pic_500;
            private String pic_640;
            private String pic_750;
            private String pic_1080;
            private int is_bought;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLength() {
                return length;
            }

            public void setLength(String length) {
                this.length = length;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getChannel_id() {
                return channel_id;
            }

            public void setChannel_id(String channel_id) {
                this.channel_id = channel_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getStatus_mask() {
                return status_mask;
            }

            public void setStatus_mask(String status_mask) {
                this.status_mask = status_mask;
            }

            public String getCommend_time() {
                return commend_time;
            }

            public void setCommend_time(String commend_time) {
                this.commend_time = commend_time;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getLike_count() {
                return like_count;
            }

            public void setLike_count(String like_count) {
                this.like_count = like_count;
            }

            public String getExchange_count() {
                return exchange_count;
            }

            public void setExchange_count(String exchange_count) {
                this.exchange_count = exchange_count;
            }

            public String getComment_count() {
                return comment_count;
            }

            public void setComment_count(String comment_count) {
                this.comment_count = comment_count;
            }

            public int getView_count() {
                return view_count;
            }

            public void setView_count(int view_count) {
                this.view_count = view_count;
            }

            public String getIs_edit() {
                return is_edit;
            }

            public void setIs_edit(String is_edit) {
                this.is_edit = is_edit;
            }

            public int getIs_pay() {
                return is_pay;
            }

            public void setIs_pay(int is_pay) {
                this.is_pay = is_pay;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public int getIs_like() {
                return is_like;
            }

            public void setIs_like(int is_like) {
                this.is_like = is_like;
            }

            public String getPic_100() {
                return pic_100;
            }

            public void setPic_100(String pic_100) {
                this.pic_100 = pic_100;
            }

            public String getPic_200() {
                return pic_200;
            }

            public void setPic_200(String pic_200) {
                this.pic_200 = pic_200;
            }

            public String getPic_500() {
                return pic_500;
            }

            public void setPic_500(String pic_500) {
                this.pic_500 = pic_500;
            }

            public String getPic_640() {
                return pic_640;
            }

            public void setPic_640(String pic_640) {
                this.pic_640 = pic_640;
            }

            public String getPic_750() {
                return pic_750;
            }

            public void setPic_750(String pic_750) {
                this.pic_750 = pic_750;
            }

            public String getPic_1080() {
                return pic_1080;
            }

            public void setPic_1080(String pic_1080) {
                this.pic_1080 = pic_1080;
            }

            public int getIs_bought() {
                return is_bought;
            }

            public void setIs_bought(int is_bought) {
                this.is_bought = is_bought;
            }
        }
    }
}
