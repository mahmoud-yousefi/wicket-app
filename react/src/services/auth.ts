import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/wicket-app/api';

export const validateToken = async () => {
    try {
        const response = await axios.get(`${API_BASE_URL}/auth/validate`, {
            withCredentials: true,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            }
        });
        return response.data;
    } catch (error: unknown) {
        console.log(error);
    }
};

export const logout = () => {
    document.cookie = "jwt_token=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;";
    redirectToLogin();
};

export const redirectToLogin = () => {
    window.location.href = 'http://localhost:8080/wicket-app/login';
};