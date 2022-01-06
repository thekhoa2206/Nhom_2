import {createUseStyles} from 'react-jss'
import { colorBlue } from '../../../theme/palette'

const useStyles = createUseStyles({
    iframe:{
      width: 600,
      height: 350,
      background: colorBlue,
    },
    title:{
      lineHeight: 50,
      textAlign: 'center',
      borderBottom: "1px solid #F5F5DC"
    },
    titleText:{
    },
    content:{
      display: 'flex'
    },
    infoPersonal:{
      width: 280,
      marginLeft: 20,
    },
    infoReservation:{
      width: 280,
      marginLeft: 20,
    }

  });

export default useStyles;
