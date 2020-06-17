import React from 'react';
// ///////////////////////
import '../sass/pages/mainPage.styles.scss';

// ////////////////////////
import Header from '../pages/header.component';
import Advantages from '../pages/Advantages.component';
import AppSection from '../pages/appSection.component';
const MainPage = () =>(
    <div className='mainPage'>
        <Header/>
        <Advantages/>
        <AppSection/>
    </div>
);

export default MainPage;