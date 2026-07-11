package classes

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotSame
import org.junit.jupiter.api.Test

// Task 6: copy() is shallow - mutable properties need copying by hand.
class T6RenamedWithIndependentMembersTest {
    @Test fun `renames the team`() {
        val team = Team("Rockets", mutableListOf("Ada", "Grace"))
        val renamed = renamedWithIndependentMembers(team, "Comets")
        assertEquals("Comets", renamed.name)
    }

    @Test fun `copy's members start out equal to the original's`() {
        val team = Team("Rockets", mutableListOf("Ada", "Grace"))
        val renamed = renamedWithIndependentMembers(team, "Comets")
        assertEquals(listOf("Ada", "Grace"), renamed.members)
    }

    @Test fun `mutating the copy's members never affects the original`() {
        val team = Team("Rockets", mutableListOf("Ada", "Grace"))
        val renamed = renamedWithIndependentMembers(team, "Comets")

        renamed.members.add("Marie")

        assertEquals(listOf("Ada", "Grace"), team.members)
        assertEquals(listOf("Ada", "Grace", "Marie"), renamed.members)
    }

    @Test fun `mutating the original's members never affects the copy`() {
        val team = Team("Rockets", mutableListOf("Ada", "Grace"))
        val renamed = renamedWithIndependentMembers(team, "Comets")

        team.members.add("Marie")

        assertEquals(listOf("Ada", "Grace", "Marie"), team.members)
        assertEquals(listOf("Ada", "Grace"), renamed.members)
    }

    @Test fun `the two members lists are different instances`() {
        val team = Team("Rockets", mutableListOf("Ada", "Grace"))
        val renamed = renamedWithIndependentMembers(team, "Comets")
        assertNotSame(team.members, renamed.members)
    }
}
