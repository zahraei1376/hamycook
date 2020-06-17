import React from 'react';
/////////////////////////////////
import axios from 'axios';
// ///////////////////////////////
import '../sass/pages/appSection.styles.scss';
import '../sass/base/utilities.scss';
import  img from'../img/phone.png';

class AppSection extends React.Component{
    constructor(){
        super()
        this.state={
            phoneNumber:''
        }
    }
    handleInput = e =>{
        this.setState({phoneNumber:e.target.value},function(){
            console.log(this.state.phoneNumber);
        });
        
    }

    clickHandle = () =>{
        axios.post('',{phoneNumber:this.state.phoneNumber})
        .then(response =>{

        })
        .catch(error =>{
            console.log(error);
        })
        // console.log(this.state.phoneNumber)
    }

    render(){
        return(
            <div className='appSection'>
                <div className='appSection__back margin-top-large'>
                    <img className='appSection__img' src={img} alt='phone'/>
                    <div className='appSection__textDiv'>
                        <h3>حامی کوک بر روی موبایل</h3>
                        <p className='appSection__text margin-top-small'>برای دریافت لینک دانلود اپلیکیشن حامی کوک شماره موبایل خود را  وارد کنید.</p>
                        <div className='appSection__group  margin-top-medium'>
                            <input type='text' className='appSection__group-input' value={this.state.phoneNumber} onChange={(e) => this.handleInput(e)} />
                            <button className='appSection__group-btn' onClick={this.clickHandle}>دریافت لینک با sms</button>
                        </div>
                    </div>
                </div>
                <div className='appSection__after margin-top-large'>
                    <h4 className='appSection__after-header'>غذا چی میل دارید انتخاب کنید</h4>
                    <button href='#' className='appSection__after-btn'>لیست همه غذاهای خوشمزه &rarr;</button>
                </div>
            </div>
            
        )
    }
};

export default AppSection;