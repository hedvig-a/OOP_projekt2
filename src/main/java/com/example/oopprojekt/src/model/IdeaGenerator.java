package com.example.oopprojekt.src.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IdeaGenerator {
    private final List<Idea> randomIdeas;
    private final List<Idea> savedIdeas;
    private final String savedIdeasFile = "src/main/resources/com/example/oopprojekt/src/ui/data/saved_ideas.txt";

    public IdeaGenerator() {
        InputStream randomIdeasFile = getClass().getResourceAsStream("/com/example/oopprojekt/src/ui/data/random_ideas.txt");

        randomIdeas = getIdeasFromFile(randomIdeasFile);
        savedIdeas = getIdeasFromFile(savedIdeasFile);
    }

    public List<Idea> getFilteredIdeas(String type) {
        return "random".equalsIgnoreCase(type) ? randomIdeas : savedIdeas;
    }

    public String addIdea(String activity, String category, String description) {
        // Ei lase sama nimega teist ideed lisada
        for (Idea existingIdea : savedIdeas) {
            if (existingIdea.getActivity().equalsIgnoreCase(activity)) {
                return "Idea with this name already exists.";
            }
        }

        Idea newIdea = Idea.create(activity, category, description);

        try (FileWriter writer = new FileWriter(savedIdeasFile, true)) {
            writer.write(newIdea.toLine() + "\n");
            savedIdeas.add(newIdea);
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Idea> getIdeasFromFile(InputStream inputStream) {
        List<Idea> ideas = new ArrayList<>();
        if (inputStream == null) return ideas;

        try (Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNextLine()) {
                Idea idea = Idea.fromLine(scanner.nextLine(), false);
                if (idea != null) ideas.add(idea);
            }
        }
        return ideas;
    }

    private List<Idea> getIdeasFromFile(String filepath) {
        File file = new File(filepath);
        List<Idea> ideas = new ArrayList<>();
        if (!file.exists()) return ideas;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                Idea idea = Idea.fromLine(scanner.nextLine(), true);
                if (idea != null) ideas.add(idea);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file: " + filepath);
        }
        return ideas;
    }
    public List<Idea> getAllIdeas() {
        return new ArrayList<>(savedIdeas);
    }

    public boolean markAsFavorite(String activity) {
        for (Idea idea : savedIdeas) {
            if (idea.getActivity().equalsIgnoreCase(activity)) {
                if (!idea.isFavorite()) {
                    idea.setFavorite(true);
                    saveIdeasToFile(savedIdeas);
                }
                return true;
            }
        }
        return false;
    }

    private void saveIdeasToFile(List<Idea> ideas) {
        try (FileWriter writer = new FileWriter(savedIdeasFile)) {
            for (Idea idea : ideas)
                writer.write(idea.toLine() + "\n");
        } catch (IOException e) {
            System.out.println("There was an error while saving the ideas. :(");
        }
    }

    public boolean removeIdea(String activity) {
        for (int i = 0; i < savedIdeas.size(); i++) {
            Idea idea = savedIdeas.get(i);
            if (idea.getActivity().equalsIgnoreCase(activity)) {
                savedIdeas.remove(i);
                saveIdeasToFile(savedIdeas);
                return true;
            }
        }
        return false;
    }

    public List<Idea> getFavorites() {
        List<Idea> favorites = new ArrayList<>();
        for (Idea idea : savedIdeas) {
            if (idea.isFavorite()) {
                favorites.add(idea);
            }
        }
        return favorites;
    }
}
