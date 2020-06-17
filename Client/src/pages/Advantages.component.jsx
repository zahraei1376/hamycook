import React from 'react';
// //////////////////
import '../sass/pages/Advantages.styles.scss';
import img1 from '../img/helthy.jpg';
import img2 from '../img/return.jpg';
import img3 from '../img/queue.jpg';
import FeautureAdvance from '../component/feautureAdvance.component';

const Advantages =() =>(
    <div className='advantages'>
        <h2 className='advantages__heading'>حامی کوک یک سامانه سفارش آنلاین غذاهای سنتی است که بهترین غذاها با کیفیت عالی به مشتریان عرضه میکند</h2>
        <FeautureAdvance img={img1} title='ما سلامتی شما را تضمین میکنیم' text=' ما در حامی کوک غذاهارا کاملا به صورت خانگی و با لازم مرغوب پخت میکنیم' />
        <FeautureAdvance img={img2} title='کیفیت غذا را تضمین میکنیم' text='در صورت عدم رضایت در اولین سفارش کل مبلغ پرداختی به حساب مشتری واریز میشود' />
        <FeautureAdvance img={img3} title='از ایستادن در صف بی‌نیازتان می‌کنیم' text='حامی کوک غذای شما را در کمتریت زمان و بدون اتلاف وقت در صف در اختیار شما قرار میدهد.' />
    </div>
);

export default Advantages;