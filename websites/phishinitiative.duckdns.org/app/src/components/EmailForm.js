import { useForm } from 'react-hook-form';
import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css'; // styling library (md3 spacings.. etc)
import { TextField } from '@mui/material';
import "@fontsource/open-sans";
import { styled } from "@mui/material/styles";
import { outlinedInputClasses } from "@mui/material/OutlinedInput";
import { inputLabelClasses } from "@mui/material/InputLabel";
import Paper from '@mui/material/Paper';
import { Dropdown } from 'semantic-ui-react'
import 'semantic-ui-css/semantic.min.css';
import {connection} from '../index.js';


const StyledTextField = styled(TextField)({


  [`& .${outlinedInputClasses.root} .${outlinedInputClasses.notchedOutline}`]: {
    borderColor: "black"
  },
  [`&:hover .${outlinedInputClasses.root} .${outlinedInputClasses.notchedOutline}`]: {
    borderColor: "red"
  },
  [`& .${outlinedInputClasses.root}.${outlinedInputClasses.focused} .${outlinedInputClasses.notchedOutline}`]: {
    borderColor: "black",
  },
  [`& .${outlinedInputClasses.input}`]: {
    color: "black",

  },
  [`&:hover .${outlinedInputClasses.input}`]: {
    color: "black"
  },
  [`& .${outlinedInputClasses.root}.${outlinedInputClasses.focused} .${outlinedInputClasses.input}`]: {
    color: "black"
  },
  [`& .${inputLabelClasses.outlined}`]: {
    color: "black"
  },
  [`&:hover .${inputLabelClasses.outlined}`]: {
    color: "black"
  },
  [`& .${inputLabelClasses.outlined}.${inputLabelClasses.focused}`]: {
    color: "black"
  }
});





const boxStyle = {
  fontFamily: 'Open Sans',
  fontSize: '25px',

  position: 'absolute', left: '50%', top: '30%',
  transform: 'translate(-50%, -50%)'
};



const textBox1 = {

  fontFamily: 'Open-Sans',
  width: '300px',
  top:'20px'

};
const textBox2 = {
  position: 'absolute',
  fontFamily: 'Open Sans',
  width: '300px',
  top: '140px',
  left: '0px'

};


const buttonStyle = {

  position: 'absolute',
  height: "40px",
  width: '300px',
  left: "00px",
  top: "360px",
  fontFamily: 'Open Sans',
  fontSize:'16px',
  fontWeight: '700',
  backgroundColor: "black",
  color: "white"


};


const options = [
  { key: 1, text: 'MyLSU Login Page', value: 1 },
  { key: 2, text: 'Victory Bank Login Page', value: 2 },
  { key: 3, text: 'Twitter Login Page', value: 3 },
  { key: 4, text: 'Netflix Login Page', value: 4 },

]

const EmailForm = () => {


  const { register,triggerValidation,setValue, reset, handleSubmit, formState: { errors } } = useForm({
    
  });

  
  const onSubmit = data => {
    //console.log(JSON.stringify(data));
    connection.send(JSON.stringify(data));
    reset()

  }

  

  return (
    <div style={boxStyle} className="form">
      <Paper elevation={12} sx={{ backgroundColor: 'white', position: 'absolute', height: 500, left: -50, top: -60, padding: 25, color: 'text.primary' }} />
      <form onSubmit={handleSubmit(onSubmit)}>
          <label style = {{position:'absolute',fontFamily:'Open Sans', top:250, left:14,fontSize:12}}> Site to send results</label>
          <label style = {{position:'absolute',fontFamily:'Open Sans', top:120, left:14,fontSize:12}}> Email to send phish tests</label>
          <label style = {{position:'absolute',fontFamily:'Open Sans', top:-4, left:14,fontSize:12}}> Email to recieve results</label>
          
          <Dropdown
            style={{height: '25px', width: '300px', top: '280px', position: 'absolute', fontSize: '15px' }}
            placeholder='Choose Site' 
            name = "dropdown"
            options={options} 
            fluid
            selection
            compact
            closeOnBlur
            
            onChange={async (e, { name, value }) => {
              setValue(name, value);
              await triggerValidation({ name });
            }}

          />        
        <StyledTextField sx={textBox1}  type="email" placeholder="YOUR EMAIL" {...register("receiveEmail", {})} />
        <StyledTextField style={textBox2} type="email" placeholder="PHISH EMAIL" {...register('phishEmail', {})} />
        <div className="button">
          <button style={buttonStyle} disableUnderlinevariant="contained" type="submit" className="btn">SEND</button>
        </div>
      </form>
    </div>
  );
}



export default EmailForm

