package fun.peri.arithmetic;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

public class StringTest {

    @Test
    void TestSplit() {
        String str = "a,b,c,,";
        int index = str.lastIndexOf(",");
        System.out.println(index);
        char c = str.charAt(index - 1);
        System.out.println("index:" + (index - 1) + ", value:" + c);
        if (!Objects.equals(null, c)) {
            if (Objects.equals(',', c)) {
                index -= 1;
                System.out.println(index);
            }
        }
        ArrayList arrayList = new ArrayList();
        arrayList.subList(0, 9);
        Arrays.asList();
        String[] array = str.split(",");
        System.out.println(array.length);
    }

    /**
     * 不要在forEach循环中remove/add
     */
    @Test
    void TestForEach() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        for (String s : list) {
            if ("2".equals(s)) {
                list.remove(s);
            }
        }
        System.out.println(list);
    }

    /**
     * remove使用Iterator
     */
    @Test
    void TestIterator() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.removeIf("2"::equals);
        System.out.println(list);
    }

    /**
     * 模仿异常情况
     */
    @Test
    void TestComparator() {
        new Comparator<Student>() {
            public int compare(Student one, Student two) {
                return one.getId() > two.getId() ? 1 : -1;
            }
        };
    }

    /**
     * 验证异常情况
     */
    @Test
    void TestCurrentHashMap() {
        ConcurrentHashMap map = new ConcurrentHashMap<>();
        map.get(null);
        System.out.println("can do this");
    }

    @Test
    void TestThread() {
//        System.out.println(new TimerTaskThread().getName());
    }

    @Test
    void TestThreadPoolExecutor() {
        Executors.newFixedThreadPool(10);
        new ThreadPoolExecutor(50, 100, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        }, new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * Instant代替date
     * LocalDateTime代替Calendar
     * DateTimeFormatter代替SimpleDateFormat
     */
    @Test
    void TestDateTimeFormatter() {
        System.out.println(Instant.now());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter);
        System.out.println(LocalDateTime.now().format(formatter));
        System.out.println(LocalDateTime.now().plusDays(1));
        System.out.println(LocalDateTime.now().minusDays(1));
    }

    /**
     * 多线程测试
     */
    @Test
    void TestScheduledExecutorService() {
        CountDownLatch latch = new CountDownLatch(5);
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        for (int i = 0; i < 5; i++) {
            final int j = i;
            executorService.schedule(() -> System.out.println("test" + j), 1, TimeUnit.MILLISECONDS);
            latch.countDown();
        }
    }

    @Test
    void TestThreadLocalRandom() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        double code = random.nextDouble(1) * 899999 + 100000;
        System.out.println(code);
        long k = (long) code;
        long j = Math.round(code);
        System.out.println(j);
        System.out.println(k);
        //-1 ~ 1
        double l = random.nextGaussian();
        System.out.println(l);
        System.out.println(random.nextLong(1000));
    }

    /**
     * 测试LongAdder
     */
    @Test
    void TestLongAdder() {
        LongAdder longAdder = new LongAdder();
        longAdder.add(1);
        System.out.println(longAdder.doubleValue());
        ThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
        for (int i = 0; i < 5; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    longAdder.add(1);
                }
            });
            System.out.println(longAdder.longValue());
        }
    }

    /**
     * ThreadLocal无法解决共享对象的更新问题，使用static进行修饰
     */
    private static final ThreadLocal<DateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    @Test
    void TestThreadLocal() {
        System.out.println(threadLocal.getClass());
    }

    /**
     * try-with-resource test
     */
    @Test
    void TestTryWithResource() {
        String basePath = System.getProperty("user.dir");
        System.out.println(basePath);
        try (
                FileInputStream inputStream = new FileInputStream(new File(basePath));
                ///抛出异常FileNotFoundException
//                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File("")))
        ) {
            System.out.println("test:" + inputStream.getClass());
//            System.out.println("test" + bufferedInputStream.getClass());
            throw new IOException();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("test try-with-resource with FileNotFoundException");
        } catch (IOException e) {
            System.out.println("test try-with-resource with IOException");
        }
    }

    @Test
    void TestOptional() {
        Optional<Student> optionalStudent = Optional.empty();
//        optionalStudent.get();
        Student student = null;
//        Optional<Student> optional = Optional.of(student);
        Optional<Student> opt = Optional.ofNullable(student);
    }

    @Test
    void TestSystemProperty() {
        System.out.println("vm.version:" + System.getProperty("java.vm.version"));
    }

    @Test
    void initPhone() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        long add = random.nextLong(10000000, 99999999);
        String[] phones = "139,138,137,136,135,134,159,158,157,150,151,152".split(",");
        int index = random.nextInt(0, phones.length - 1);
        String phoneHead = phones[index];
        String phone = phoneHead + add;
        System.out.println(phone);
    }


    @Test
    void initName() {
        String[] firstName = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈",
                "韩", "杨", "朱", "秦", "尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶",
                "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎",
                "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费",
                "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常", "乐", "于",
                "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和", "穆", "萧",
                "尹", "姚", "邵", "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴",
                "谈", "宋", "茅", "庞", "熊", "纪", "舒", "屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闵", "席",
                "季", "麻", "强", "贾", "路", "娄", "危", "江", "童", "颜", "郭", "梅", "盛", "林", "刁", "钟", "徐",
                "邱", "骆", "高", "夏", "蔡", "田", "樊", "胡", "凌", "霍", "虞", "万", "支", "柯", "昝", "管", "卢",
                "莫", "经", "房", "裘", "缪", "干", "解", "应", "宗", "丁", "宣", "贲", "邓", "郁", "单", "杭", "洪",
                "包", "诸", "左", "石", "崔", "吉", "钮", "龚", "程", "嵇", "邢", "滑", "裴", "陆", "荣", "翁", "荀",
                "羊", "于", "惠", "甄", "曲", "家", "封", "芮", "羿", "储", "靳", "汲", "邴", "糜", "松", "井", "段",
                "富", "巫", "乌", "焦", "巴", "弓", "牧", "隗", "山", "谷", "车", "侯", "宓", "蓬", "全", "郗", "班",
                "仰", "秋", "仲", "伊", "宫", "宁", "仇", "栾", "暴", "甘", "钭", "厉", "戎", "祖", "武", "符", "刘",
                "景", "詹", "束", "龙", "叶", "幸", "司", "韶", "郜", "黎", "蓟", "溥", "印", "宿", "白", "怀", "蒲",
                "邰", "从", "鄂", "索", "咸", "籍", "赖", "卓", "蔺", "屠", "蒙", "池", "乔", "阴", "郁", "胥", "能",
                "苍", "双", "闻", "莘", "党", "翟", "谭", "贡", "劳", "逄", "姬", "申", "扶", "堵", "冉", "宰", "郦",
                "雍", "却", "璩", "桑", "桂", "濮", "牛", "寿", "通", "边", "扈", "燕", "冀", "浦", "尚", "农", "温",
                "别", "庄", "晏", "柴", "瞿", "阎", "充", "慕", "连", "茹", "习", "宦", "艾", "鱼", "容", "向", "古",
                "易", "慎", "戈", "廖", "庾", "终", "暨", "居", "衡", "步", "都", "耿", "满", "弘", "匡", "国", "文",
                "寇", "广", "禄", "阙", "东", "欧", "殳", "沃", "利", "蔚", "越", "夔", "隆", "师", "巩", "厍", "聂",
                "晁", "勾", "敖", "融", "冷", "訾", "辛", "阚", "那", "简", "饶", "空", "曾", "毋", "沙", "乜", "养",
                "鞠", "须", "丰", "巢", "关", "蒯", "相", "查", "后", "荆", "红", "游", "郏", "竺", "权", "逯", "盖",
                "益", "桓", "公", "仉", "督", "岳", "帅", "缑", "亢", "况", "郈", "有", "琴", "归", "海", "晋", "楚",
                "闫", "法", "汝", "鄢", "涂", "钦", "商", "牟", "佘", "佴", "伯", "赏", "墨", "哈", "谯", "篁", "年",
                "爱", "阳", "佟", "言", "福", "南", "火", "铁", "迟", "漆", "官", "冼", "真", "展", "繁", "檀", "祭",
                "密", "敬", "揭", "舜", "楼", "疏", "冒", "浑", "挚", "胶", "随", "高", "皋", "原", "种", "练", "弥",
                "仓", "眭", "蹇", "覃", "阿", "门", "恽", "来", "綦", "召", "仪", "风", "介", "巨", "木", "京", "狐",
                "郇", "虎", "枚", "抗", "达", "杞", "苌", "折", "麦", "庆", "过", "竹", "端", "鲜", "皇", "亓", "老",
                "是", "秘", "畅", "邝", "还", "宾", "闾", "辜", "纵", "侴", "万俟", "司马", "上官", "欧阳", "夏侯",
                "诸葛", "闻人", "东方", "赫连", "皇甫", "羊舌", "尉迟", "公羊", "澹台", "公冶", "宗正", "濮阳", "淳于", "单于",
                "太叔", "申屠", "公孙", "仲孙", "轩辕", "令狐", "钟离", "宇文", "长孙", "慕容", "鲜于", "闾丘", "司徒", "司空",
                "兀官", "司寇", "南门", "呼延", "子车", "颛孙", "端木", "巫马", "公西", "漆雕", "车正", "壤驷", "公良", "拓跋",
                "夹谷", "宰父", "谷梁", "段干", "百里", "东郭", "微生", "梁丘", "左丘", "东门", "西门", "南宫", "第五", "公仪",
                "公乘", "太史", "仲长", "叔孙", "屈突", "尔朱", "东乡", "相里", "胡母", "司城", "张廖", "雍门", "毋丘", "贺兰",
                "綦毋", "屋庐", "独孤", "南郭", "北宫", "王孙"};
        String girl = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎" +
                "锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚" +
                "宜可姬舒影荔枝思丽";
        String boy = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪" +
                "博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄" +
                "琛钧冠策腾楠榕风航弘";
        ThreadLocalRandom random = ThreadLocalRandom.current();
        String sex = System.currentTimeMillis() % 2 == 0 ? "0" : "1";
        int nameLength = random.nextInt() % 2 == 0 ? 2 : 3;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(firstName[random.nextInt(0, firstName.length - 1)]);

        if (Objects.equals("0", sex)) {
            for (int i = 0; i < nameLength - 1; i++) {
                stringBuilder.append(girl.charAt(random.nextInt(0, girl.length() - 1)));
            }
        } else {
            for (int i = 0; i < nameLength - 1; i++) {
                stringBuilder.append(boy.charAt(random.nextInt(0, girl.length() - 1)));
            }
        }
        System.out.println(stringBuilder.toString());
    }

    /**
     * 以当前时间为基准生成的随机时间
     *
     * @since jdk1.8+
     */
    @Test
    void initDate() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String time;
        if (System.currentTimeMillis() % 2 == 0) {
            if (random.nextInt(1, 10000) % 2 == 0) {
                time = now.format(dateTimeFormatter);
            } else {
                time = now.minusHours(random.nextInt(0, 24 + 1)).format(dateTimeFormatter);
            }
        } else {
            if (random.hashCode() % 2 == 0) {
                time = now.minusMonths(random.nextInt(0, 6 + 1)).format(dateTimeFormatter);
            } else {
                time = now.minusDays(random.nextInt(0, 31 + 1)).format(dateTimeFormatter);
            }
        }
        System.out.println(time);
    }

    @Test
    void testThreadLocalRandom() {
        System.out.println((int) '1');
        for (int i = 0; i < 50; i++) {
            System.out.print(ThreadLocalRandom.current().nextInt(0, 10) + ",");
        }
    }

    @Test
    void testHashMap() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        for (Map.Entry obj : map.entrySet()) {
            System.out.println(obj.getKey());
            System.out.println(map.values());
        }
    }

    /**
     * classloader
     */
    @Test
    void testClassLoader(){
        ClassLoader loader = StringTest.class.getClassLoader();
        System.out.println(loader);
        ClassLoader c1 = loader.getParent();
        System.out.println(c1);
        ClassLoader c2 = c1.getParent();
        System.out.println(c2);
//        ClassLoader c3 = c2.getParent();
//        System.out.println(c3);
    }

}



