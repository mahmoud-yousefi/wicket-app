import axios from 'axios';
import { getCookie } from '../utils';

const API_BASE_URL = 'http://localhost:8080/wicket-app/api';

export const validateToken = async () => {
    try {
        const jwtToken = getCookie('jwt_token');

        const response = await axios.get(`${API_BASE_URL}/auth/validate`, {
            headers: {
                Cookie: `jwt_token=${jwtToken}`, 
            },
            withCredentials: true, 
        });
        return response.data;
    } catch (error: unknown) {
        console.log(error);
    }
};

export const redirectToLogin = () => {
    window.location.href = 'http://localhost:8080/wicket-app/login';
};