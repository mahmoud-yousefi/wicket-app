import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/wicket-app/api';

export const validateToken = async () => {
    try {
        const response = await axios.get(`${API_BASE_URL}/auth/validate`, {
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