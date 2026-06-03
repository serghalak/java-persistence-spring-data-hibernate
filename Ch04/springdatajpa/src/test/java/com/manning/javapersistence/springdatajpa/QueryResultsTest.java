package com.manning.javapersistence.springdatajpa;

import com.manning.javapersistence.springdatajpa.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.data.domain.Sort.*;
import static org.springframework.data.jpa.domain.JpaSort.*;

public class QueryResultsTest extends SpringDataJpaApplicationTests {

    @Test
    void testStreamable() {
        try(Stream<User> result =
                    userRepository.findByEmailContaining("someother")
                            .and(userRepository.findByLevel(2))
                            .stream().distinct()) {
            //assertEquals(6, result.count());
        }
    }

    @Test
    void testFindByAsArrayAndSort() {
        int numberOfUsersByActivity = userRepository.findNumberOfUsersByActivity(true);
        List<User> userList1 = userRepository.findByLevelAndActive(1, true);
        int active = userRepository.findNumberOfUsersByActivityNative(true);

        List<Object[]> usersList1 =
                userRepository.findByAsArrayAndSort("ar", by("username"));
        List<Object[]> usersList2 =
                userRepository.findByAsArrayAndSort("ar",
                        by("email_length").descending());
        List<Object[]> usersList3 = userRepository.findByAsArrayAndSort(
                "ar", unsafe("LENGTH(u.email)"));
        assertAll(
                () -> assertEquals(8, active),
                () -> assertEquals(8, numberOfUsersByActivity),
                () -> assertEquals(2, usersList1.size()),
                () -> assertEquals("darren", usersList1.get(0)[0]),
                () -> assertEquals(21, usersList1.get(0)[1]),
                () -> assertEquals(2, usersList2.size()),
                () -> assertEquals("marion", usersList2.get(0)[0]),
                () -> assertEquals(26, usersList2.get(0)[1]),
                () -> assertEquals(2, usersList3.size()),
                () -> assertEquals("darren", usersList3.get(0)[0]),
                () -> assertEquals(21, usersList3.get(0)[1])
        );
    }
}
