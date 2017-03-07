package com.example.arnaudetitia.offlinemodule.data;

import com.example.arnaudetitia.offlinemodule.beans.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arnaud ETITIA on 21/12/2016.
 */
public class Questions {

    public static List<Question> mQuestions = new ArrayList<>();

    public static void initQuestions(){
        mQuestions.clear();
        mQuestions.add(new Question(1,"Quel pays a pour hymne la Brabançonne ?",ReponseType.PAYS));
        mQuestions.add(new Question(2,"Quel pays a eu pour président Lech Walesa ?",ReponseType.PAYS));
        mQuestions.add(new Question(3,"Quel pays étudie un helléniste ?",ReponseType.PAYS));
        mQuestions.add(new Question(4,"Dans quelle ville peut-on se balader dans le quartier du Panier ? ",ReponseType.VILLE));
        mQuestions.add(new Question(5,"Dans quelle ville se trouve la plus vaste cathédrale de France ?",ReponseType.VILLE));
        mQuestions.add(new Question(6,"Quelle ville a été chanté par Téléphone et Frank Sinatra ?",ReponseType.VILLE));
        mQuestions.add(new Question(7,"Johann Strauss a composé le Beau Danube ...",ReponseType.COULEUR));
        mQuestions.add(new Question(8,"Quelle est la couleur de la marque dans la BD Blake et Mortimer ?",ReponseType.COULEUR));
        mQuestions.add(new Question(9,"Quelle est la couleur du bonnet du commandant Cousteau ?",ReponseType.COULEUR));
        mQuestions.add(new Question(10,"Dans quel pays se situe le Kremlin",ReponseType.PAYS));
        mQuestions.add(new Question(11,"Qui a rempoté l'Euro de football en 2004 ?",ReponseType.PAYS));
        mQuestions.add(new Question(12,"De quel pays est originaire la chanteuse Nina Hagen ?",ReponseType.PAYS));
        mQuestions.add(new Question(13,"Dans quelle ville s'est déroulé le \"match du siècle\" France-RFA de 1982 ?",ReponseType.VILLE));
        mQuestions.add(new Question(14,"Quelle est la préfecture du département de la Gironde ?",ReponseType.VILLE));
        mQuestions.add(new Question(15,"Au sud de quelle ville se trouve le stade de Twickenham ?",ReponseType.VILLE));
        mQuestions.add(new Question(16,"De quel couleur est l'or, quand il s'agit de pétrole ?",ReponseType.COULEUR));
        mQuestions.add(new Question(17,"Quelle est la couleur de la pierre appelée Lapis-Lazuli ?",ReponseType.COULEUR));
        mQuestions.add(new Question(18,"De quelle couleur Aubergine est-elle une nuance ?",ReponseType.COULEUR));
        mQuestions.add(new Question(19,"Quelle couleur est associée au club de Saint-Etienne?",ReponseType.COULEUR));
        mQuestions.add(new Question(20,"Qui a pour véritable prénom Wanda ?",ReponseType.CHANTEUR));
        mQuestions.add(new Question(21,"Qui chante sur la bande originale du film \"Meurs un autre jour\" ?",ReponseType.CHANTEUR));
        mQuestions.add(new Question(22,"Qui a fait partie de la \"Sexion d'assaut\" ?",ReponseType.CHANTEUR));
        mQuestions.add(new Question(23,"En 2008, qui chante le titre \"Womanizer\"?",ReponseType.CHANTEUR));
        mQuestions.add(new Question(24,"A qui doit-on les titres \"Les Ricains\" et \"Les Bals Populaires\" ?",ReponseType.CHANTEUR));
        mQuestions.add(new Question(25,"Quel Premier Ministre français est né à Barcelone ?",ReponseType.POLITIQUE));
        mQuestions.add(new Question(26,"Qui perd face à François Mitterrand aux élections présidentielles de 1988 ?",ReponseType.POLITIQUE));
        mQuestions.add(new Question(27,"Qui fut le premier Premier Ministre de Jacques Chirac en 1995 ?",ReponseType.POLITIQUE));
        mQuestions.add(new Question(28,"Quel homme politique fut également présentateur du JT et chanteur ?",ReponseType.POLITIQUE));
        mQuestions.add(new Question(29,"Qui fut gouverneur de l'Arkansas avant d'effectuer deux mandats présidentiels ?",ReponseType.POLITIQUE));
        mQuestions.add(new Question(30,"Quel ancien ministre a été chroniqueuse dans \"Le Grand 8\" ?",ReponseType.POLITIQUE));
    }

    public static Question getQuestionById(int id){
        for(Question q : mQuestions){
            if (q.getId() == id){
                return q;
            }
        }
        return null;
    }

    public static Question getQuestionByText(String question) {
        for (Question q : mQuestions) {
            if (question.equals(q.getQuestion())) {
                return q;
            }
        }
        return null;
    }

    public static int getNbQuestions(){
        return mQuestions.size();
    }
}
