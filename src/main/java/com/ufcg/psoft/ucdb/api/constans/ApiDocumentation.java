package com.ufcg.psoft.ucdb.api.constans;

public class ApiDocumentation {
    public static class Endpoint {
        public static final String SIGNUP_ENDPOINT = "sign";
        public static final String SIGNIN_ENDPOINT = "singup";
        public static final String SUBJECT_ENDPOINT = "subject";
        public static final String SUBJECT_PATH = "/{subjectId}";
    }

    public static class ApiInfo {
        public static final String API_TITLE = "UCDb";
        public static final String API_DESCRIPTION = "O UFCG Cursos database é uma aplicação para classificação e reviews de disciplinas de cursos da UFCG.\n" +
                "\n" +
                "O UCDb disponibiliza um perfil para cada disciplina onde é possível realizar comentários e avaliações. Além disso, também é mostrado o ranking das disciplinas de acordo com o critério escolhido.\n" +
                "\n" +
                "Os usuários irão construir conteúdo sobre as disciplinas de forma colaborativa através de comentários e likes nas disciplinas.";
        public static final String CONTACT_NAME = "UCDb";
        public static final String CONTACT_URL = "";
        public static final String CONTACT_EMAIL = "";
    }

    public static class Subject {
        public static final String API_DESCRIPTION = "Manages subjects and everything related to them, comments, likes and ranking.";
        public static final String SEARCH_OPERATION = "Search subjects by name.";
        public static final String SUBSTRING_TO_SEARCH = "Substring that will be used to search the subjects";
        public static final String GET_BY_ID_OPERATION = "Get a specific subject.";
        public static final String ADD_COMMENT_OPERATION = "Add a user's comment to a subject";
        public static final String COMMENT_ENTITY = "The body of a one comment, which is basically a message.";
        public static final String ADD_REPLY_OPERATION = "Add a reply to a comment on a subject";
        public static final String DELETE_COMMENT_OPERATION = "Deletes a user comment from a subject.";
        public static final String ADD_LIKE_OPERATION = "Adds a user like for a subject";
        public static final String DELETE_LIKE_OPERATION = "Deletes a user like for a subject";
        public static final String GET_RANKING_OPERATION = "Get the ranking of the subject according to an ordering parameter";

        public static final String SUBJECT_ID = "The ID of the specific subject.";
        public static final String COMMENT_ID = "The ID of the specific comment.";
        public static final String RANKING_METHOD = "Parameter of ranking of the subjects";
    }

    public static class Signup {
        public static final String API_DESCRIPTION = "Manages the user registry in the system.";
        public static final String REGISTER_USER = "Register a user in the system and send a confirmation email.";
        public static final String USER_BODY = "The data about the user that will be registered";
        public static final String AUTHENTICATE_USER = "Authenticate an user returning an Iguassu token " +
                "from OAuth Authorization Code";
        public static final String AUTHORIZATION_CODE = "The body of the request must specify a valid OAuth2" +
                "Authorization Code";
    }

    public static class CommonParameters {
        public static final String USER_CREDENTIALS = "The header of the request must specify a valid Iguassu Token " +
                "and User Identifier";
        public static final String OAUTH_CREDENTIALS = "The header of the request must contain the right client id, " +
                "secret and redirect uri";

    }
}
