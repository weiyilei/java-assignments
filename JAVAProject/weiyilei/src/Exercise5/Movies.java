package Exercise5;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Movies
{
    public static List<Movie> readMovies(String filename) throws IOException
    {
        List<Movie> movies = new ArrayList<>();
        try (Scanner in = new Scanner(new File(filename)))
        {
            while (in.hasNextLine())
            {
                String nameLine = in.nextLine();
                String yearLine = in.nextLine();
                String directorsLine = in.nextLine();
                String producersLine = in.nextLine();
                String actorsLine = in.nextLine();
                movies.add(new Movie(getString(nameLine),
                        Integer.parseInt(getString(yearLine)),
                        getList(directorsLine), getList(producersLine),
                        getList(actorsLine)));
            }
        }
        return movies;
    }

    private static String getString(String line)
    {
        int colon = line.indexOf(":");
        return line.substring(colon + 1).trim();
    }

    private static List<String> getList(String line)
    {
        return Stream.of(getString(line).split(", "))
                .collect(Collectors.toList());
      /* Alternative without streams:
      ArrayList<String> result = new ArrayList<>();
      for (String s : getString(line).split(", "))
      {
         result.add(s);
      }
      return result;
      */
    }

    public static void main(String[] args) throws IOException
    {
        List<Movie> movieList = readMovies("movies.txt");
        List<Movie> movieList1 = readMovies("movies.txt");
        //P19_10
        System.out.println(movieList.stream()
                        .parallel()
                .collect(HashSet::new,
                        (set,i) -> set.addAll(i.getActors()),
                        AbstractCollection::addAll).size());
        //P19_11
        HashMap<String,Integer> map = movieList1.stream()
                .parallel()
                .collect(HashMap::new,
                        (hashMap, movie) -> {for(int i = 0; i < movie.getActors().size();i++){
                            hashMap.merge(movie.getActors().get(i),1,Integer::sum);
                        }
                        },
                        (HashMap1,HashMap2) -> {
                            HashMap2.forEach((key,value) -> HashMap1.merge(key,value,Integer::sum));
                        });
        Set<Map.Entry<String, Integer>> mapEntries = map.entrySet();
        List<Map.Entry<String, Integer>> result = new LinkedList<>(mapEntries);
        Collections.sort(result, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        int sort = 1;
        for (Map.Entry<String, Integer> newEntry : result) {
            if (sort <= 100) {
                System.out.println(newEntry.getKey() + ":" + newEntry.getValue());
                ++sort;
            }
        }
//        List<String> result1 = movieList.stream()
//                .map(m -> m.getTitle())
//                .filter(t -> t.startsWith("X"))
//                .collect(Collectors.toList());
//        System.out.println("Movies that start with X: "
//                + result1);
//
//        long count = movieList.stream()
//                .map(m -> intersect(m.getDirectors(), m.getActors()))
//                .filter(l -> l.size() > 0)
//                .count();
//        System.out.println("Movies in which directors are actors: "
//                + count);
//        count = movieList.stream()
//                .filter(m -> commonActorAndDirector(m))
//                .count();
//        System.out.println("Movies in which directors are actors: "
//                + count);
//
//        int result2 = movieList.stream()
//                .mapToInt(m -> m.getActors().size())
//                .max()
//                .orElse(0);
//        System.out.println("The most actors in a movie: " + result2);
//
//        movieList.stream()
//                .max((a, b) -> a.getActors().size() - b.getActors().size())
//                .ifPresent(m -> System.out.println("Movie with most actors: " + m));
//      /* Alternative:
//      movieList.stream()
//         .max(Comparator.comparing(m -> m.getActors().size()))
//	     .ifPresent(m -> System.out.println("Movie with most actors: " + m));
//      */
//
//        Map<String, Long> firstLetters = movieList.stream()
//                .collect(Collectors.groupingBy(
//                        m -> m.getTitle().substring(0, 1),
//                        Collectors.counting()));
//        System.out.println("Movies by first letter: " + firstLetters);
//
//        System.out.println("Movies starting with The: " +
//                movieList.stream()
//                        .filter(m -> m.getTitle().startsWith("The "))
//                        .count());
//
//        Map<String, List<Movie>> moviesByDirector = movieList.stream()
//                .filter(m -> m.getDirectors().size() > 0)
//                .collect(Collectors.groupingBy(
//                        m -> m.getDirectors().get(0)));
//        moviesByDirector.remove("");
//
//        System.out.println("Directors in the database: " + moviesByDirector.size());
//        String mostProlificDirector = Collections.max(
//                moviesByDirector.entrySet(),
//                Comparator.comparing(e -> e.getValue().size())).getKey();
//        System.out.println(mostProlificDirector + " directed " + moviesByDirector.get(mostProlificDirector).size() + " movies:");
//
//        List<String> titles = moviesByDirector.get(mostProlificDirector)
//                .stream()
//                .map(m -> m.getTitle())
//                .collect(Collectors.toList());
//        System.out.println(titles);
    }

    public static boolean commonActorAndDirector(Movie m)
    {
        return m.getDirectors().stream().anyMatch(d -> m.getActors().contains(d));
    }

    public static Set<String> intersect(Collection<String> a, Collection<String> b)
    {
        Set<String> intersection = new HashSet<>(a);
        intersection.retainAll(b);
        return intersection;
    }

    public static boolean isSorted(List<String> names)
    {
        List<String> lastNames = names.stream().map(n -> n.substring(n.lastIndexOf(" ") + 1)).collect(Collectors.toList());
        return lastNames.equals(lastNames.stream().sorted().collect(Collectors.toList()));
    }
}

